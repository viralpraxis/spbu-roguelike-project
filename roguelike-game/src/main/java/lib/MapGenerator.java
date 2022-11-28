package lib;

import java.util.Random;

import models.Level;
import repositories.Configuration;

public class MapGenerator {
    private LevelGenerator levelGenerator;

    private Size size;
    private int minLevelsCount;
    private int maxLevelsCount;

    public MapGenerator(Size size, int minLevelsCount, int maxLevelsCount) {
        this.levelGenerator = new LevelGenerator(size);

        this.size = size;
        this.minLevelsCount = minLevelsCount;
        this.maxLevelsCount = maxLevelsCount;
    }

    public Level[] generate() {
        int levelsCount = new Random().nextInt(maxLevelsCount - minLevelsCount) + minLevelsCount;
        Level[] levels = new Level[levelsCount];

        for (int i = 0; i < levelsCount; i++) levels[i] = levelGenerator.generate();

        return levels;
    }
}
