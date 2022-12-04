package lib;

import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;

import repositories.LevelRepository;
import repositories.Configuration;
import models.Level;


class LevelProvider {
    private static LevelProvider instance;

    private boolean isInitialized = false;
    private Iterator<Level> predefinedLevelsIterator;
    private LevelGenerator levelGenerator;
    private Random random = new Random(Configuration.getRandomSeed());

    private double predefinedToGeneratedRatio;

    public static Level get() {
        if (instance == null) { instance = new LevelProvider(); }

        return instance._get();
    }

    private Level _get() {
        if (!isInitialized) initialize();

        if (predefinedLevelsIterator.hasNext() && shouldUsePredefinedLevel()) {
            return predefinedLevelsIterator.next();
        } else {
            return levelGenerator.generate();
        }
    }

    private boolean shouldUsePredefinedLevel() {
        return random.nextDouble() > predefinedToGeneratedRatio;
    }

    private void initialize() {
        var levelsList = new ArrayList<Level>(Arrays.asList(new LevelRepository().getLevels()));
        Collections.shuffle(levelsList);
        this.predefinedLevelsIterator = levelsList.iterator();

        this.levelGenerator = new LevelGenerator(
            (Integer) Configuration.get("screen.width"),
            (Integer) Configuration.get("screen.height")
        );
        this.predefinedToGeneratedRatio = (Double) Configuration.get("level_generation.predefined_to_generated_ratio");

        this.isInitialized = true;
    }
}
