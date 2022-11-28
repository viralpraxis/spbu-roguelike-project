package lib;

import java.util.Objects;

import models.Level;
import models.mobs.MobGeneratorFactory;
import repositories.MapRepository;

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

  public MapBuilder setLevelsCount(int minimum, int maximum) {
    if (minimum <= 0 || maximum <= 0) throw new IllegalArgumentException("Minimum and maximum should be positive");
    if (minimum > maximum) throw new IllegalArgumentException("Minimum should be lower than maximum");

    this.minimumLevelsCount = minimum;
    this.maximumLevelsCount = maximum;

    return this;
  }

  public MapBuilder setLevelsCount(int count) {
    if (count <= 0) throw new IllegalArgumentException("Count should be positive");

    this.minimumLevelsCount = count;
    this.maximumLevelsCount = count;

    return this;
  }

  public MapBuilder setMaximumLevelsCount(int count) {
    if (count <= 0) throw new IllegalArgumentException("Count must be positive");

    this.maximumLevelsCount = count;

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
