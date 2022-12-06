package lib;

public class Size {
  private int width;
  private int height;

  public Size(int width, int height) {
    if (width <= 0 || height <= 0) throw new IllegalArgumentException("Both width and height should be positive");

    this.width = width;
    this.height = height;
  }

  public int width() {
    return this.width;
  }

  public int height() {
    return this.height;
  }
}
