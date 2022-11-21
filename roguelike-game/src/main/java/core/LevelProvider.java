package core;

import java.util.Random;

import repositories.LevelRepository;
import repositories.Configuration;
import models.Level;
import lib.LevelGenerator;


class LevelProvider {
    private boolean isInitialized = false;
    private Level[] predefinedLevels;
    private LevelGenerator levelGenerator;
    private Random random = new Random();

    private final double predefinedToGeneratedRatio = 0.5;

    public Level get() {
        if (!isInitialized) initialize();

        if (random.nextDouble() > predefinedToGeneratedRatio) {
            return predefinedLevels[0];
        } else {
            return levelGenerator.generate(
                (Integer) Configuration.get("screen.width"),
                (Integer) Configuration.get("screen.height")
            );
        }
    }

    private void initialize() {
        this.predefinedLevels = new LevelRepository().getLevels();
        this.levelGenerator = new LevelGenerator();

        this.isInitialized = true;
    }
}
