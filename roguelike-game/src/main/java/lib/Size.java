package lib;

public record Size(int width, int height) {
  public Size {
    if (width <= 0 || height <= 0) throw new IllegalArgumentException("Both width and height should be positive");
  }
}
