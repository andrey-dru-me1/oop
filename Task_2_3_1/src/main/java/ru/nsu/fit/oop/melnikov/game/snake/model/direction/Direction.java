package ru.nsu.fit.oop.melnikov.game.snake.model.direction;

import java.util.function.UnaryOperator;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public enum Direction {
  LEFT(point -> new Point<>(point.getX() - 1, point.getY())),
  RIGHT(point -> new Point<>(point.getX() + 1, point.getY())),
  UP(point -> new Point<>(point.getX(), point.getY() - 1)),
  DOWN(point -> new Point<>(point.getX(), point.getY() + 1));

  private final UnaryOperator<Point<Integer>> shiftPoint;

  Direction(UnaryOperator<Point<Integer>> shiftPoint) {
    this.shiftPoint = shiftPoint;
  }

  /**
   * Checks whether two directions is opposite to each other or not.
   *
   * @param directionToCompare direction to compare with {@code this}
   * @return true if two directions are opposite and false otherwise
   */
  public boolean isOpposite(Direction directionToCompare) {
    return this == Direction.DOWN && directionToCompare == Direction.UP
        || this == Direction.UP && directionToCompare == Direction.DOWN
        || this == Direction.RIGHT && directionToCompare == Direction.LEFT
        || this == Direction.LEFT && directionToCompare == Direction.RIGHT;
  }

  /**
   * Returns next of the specified point according to current direction: RIGHT returns {@code new
   * Point(x + 1, y)}, DOWN returns {@code new Point(x, y + 1)} etc.
   *
   * @param intPoint point to shift
   * @return shifted point
   */
  public Point<Integer> nextPoint(Point<Integer> intPoint) {
    return this.shiftPoint.apply(intPoint);
  }
}
