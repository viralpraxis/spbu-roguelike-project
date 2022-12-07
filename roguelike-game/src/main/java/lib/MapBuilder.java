package lib;

import java.util.Objects;

import models.Level;
import models.mobs.MobGeneratorFactory;
import repositories.MapRepository;
import lib.Interval;

public class MapBuilder {
  private GenerationMethod generationMethod;
  private Size size;
  private Interval<Integer> levelsCountInterval;
  private MobGeneratorFactory mobGeneratorFactory;

  public enum GenerationMethod {
    PREDEFINED,
    RANDOMIZED
  }

  public MapBuilder() {
    initializeDefaultValues();
  }

  public Level[] build() {
    Level[] map;

    if (this.generationMethod == GenerationMethod.RANDOMIZED) {
      map = new MapGenerator(this.size, this.levelsCountInterval, this.mobGeneratorFactory).generate();
    } else {
      map = new MapRepository().getMap();
    }

    return map;
  }

  public MapBuilder setGenerationMethod(GenerationMethod generationMethod) {
    Objects.requireNonNull(generationMethod);

    this.generationMethod = generationMethod;

    return this;
  }

  public MapBuilder setSize(Size size) {
    Objects.requireNonNull(size);

    this.size = size;

    return this;
  }

  public MapBuilder setLevelsCountInterval(Interval<Integer> levelsCountInterval) {
    if (levelsCountInterval.left() <= 0 || levelsCountInterval.right() <= 0) {
      throw new IllegalArgumentException("Minimum and maximum should be positive");
    }

    this.levelsCountInterval = levelsCountInterval;

    return this;
  }

  public MapBuilder setMobGeneratorFactory(MobGeneratorFactory mobGeneratorFactory) {
    this.mobGeneratorFactory = mobGeneratorFactory;

    return this;
  }

  private void initializeDefaultValues() {
    this.generationMethod = GenerationMethod.RANDOMIZED;
    this.size = new Size(100, 48);
    this.levelsCountInterval = new Interval<Integer>(3, 6);
  }
}
