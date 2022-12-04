package lib;

import java.util.Random;

import models.Level;
import repositories.Configuration;

public class MapGenerator {
    private static MapGenerator instance;

    private int minLevelsAmount;
    private int maxLevelsAmount;

    private Random random;

    public static Level[] generate() {
        if (instance == null) instance = new MapGenerator();

        return instance._generate();
    }

    private MapGenerator() {
        initialize();
    }

    private Level[] _generate() {
        var levelsAmount = this.random.nextInt(maxLevelsAmount - minLevelsAmount) + minLevelsAmount;
        var levels = new Level[levelsAmount];

        for (int i = 0; i < levelsAmount; i++) levels[i] = LevelProvider.get();

        return levels;
    }

    private void initialize() {
        this.minLevelsAmount = (Integer) Configuration.get("map_generation.minumum_levels_count");
        this.maxLevelsAmount = (Integer) Configuration.get("map_generation.maximum_levels_count");
        this.random = new Random(Configuration.getRandomSeed());
    }
}
