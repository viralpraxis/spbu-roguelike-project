package lib;

public class Interval<T extends Comparable<T>> {
  private T left;
  private T right;

  public Interval(T left, T right) {
    if (left.compareTo(right) > 0) throw new IllegalArgumentException("Left value should not be bigger than right one");

    this.left = left;
    this.right = right;
  }

  public T left() {
    return this.left;
  }

  public T right() {
    return this.right;
  }
}
