package lib;

import java.util.Random;

import models.Level;
import models.mobs.MobGeneratorFactory;
import lib.Interval;

public class MapGenerator {
    private LevelGenerator levelGenerator;

    private Size size;
    private Interval<Integer> levelsCountInterval;
    private MobGeneratorFactory mobGeneratorFactory;

    public MapGenerator(Size size, Interval<Integer> levelsCountInterval, MobGeneratorFactory mobGeneratorFactory) {
        this.levelGenerator = new LevelGenerator(size, mobGeneratorFactory);

        this.size = size;
        this.levelsCountInterval = levelsCountInterval;
        this.mobGeneratorFactory = mobGeneratorFactory;
    }

    public Level[] generate() {
        int levelsCount = new Random().nextInt(
          levelsCountInterval.right() - levelsCountInterval.left()
        ) + levelsCountInterval.left();
        Level[] levels = new Level[levelsCount];

        for (int i = 0; i < levelsCount; i++) levels[i] = levelGenerator.generate();

        return levels;
    }
}
