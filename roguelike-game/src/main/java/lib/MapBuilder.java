package lib;

import java.util.Objects;

import models.Level;
import models.mobs.MobGeneratorFactory;
import repositories.MapRepository;
import lib.Interval;

public class MapBuilder {
  private GenerationMethod generationMethod;
  private Size size;
  private int minimumLevelsCount;
  private int maximumLevelsCount;
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
      map = new MapGenerator(this.size, this.minimumLevelsCount, this.maximumLevelsCount).generate();
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

  public MapBuilder setSize(int width, int height) {
    this.size = new Size(width, height);

    return this;
  }

  public MapBuilder setLevelsCountInterval(Interval<Integer> interval) {
    if (interval.left() <= 0 || interval.right() <= 0) throw new IllegalArgumentException("Minimum and maximum should be positive");

    this.minimumLevelsCount = interval.left();
    this.maximumLevelsCount = interval.right();

    return this;
  }

  public MapBuilder setMobGeneratorFactory(MobGeneratorFactory mobGeneratorFactory) {
    this.mobGeneratorFactory = mobGeneratorFactory;

    return this;
  }

  private void initializeDefaultValues() {
    this.generationMethod = GenerationMethod.RANDOMIZED;
    this.size = new Size(100, 48);
    this.minimumLevelsCount = 3;
    this.maximumLevelsCount = 6;
  }
}
