package lib;

import java.util.Random;

import models.Level;
import models.mobs.MobGeneratorFactory;
import repositories.Configuration;
import lib.Interval;


public class MapGenerator {
    private LevelGenerator levelGenerator;

    private Size size;
    private Interval<Integer> levelsCountInterval;
    private MobGeneratorFactory mobGeneratorFactory;

    private Random random;

    public MapGenerator(Size size, Interval<Integer> levelsCountInterval, MobGeneratorFactory mobGeneratorFactory) {
        this.levelGenerator = new LevelGenerator(size, mobGeneratorFactory);

        this.size = size;
        this.levelsCountInterval = levelsCountInterval;
        this.mobGeneratorFactory = mobGeneratorFactory;

        this.random = new Random(Configuration.getRandomSeed());
    }

    public Level[] generate() {
        int levelsCount = this.random.nextInt(
          levelsCountInterval.right() - levelsCountInterval.left()
        ) + levelsCountInterval.left();
        Level[] levels = new Level[levelsCount];

        for (int i = 0; i < levelsCount; i++) levels[i] = levelGenerator.generate();

        return levels;
    }
}
