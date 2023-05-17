package ru.nsu.fit.oop.melnikov.game.snake.model.direction;

import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

import java.util.function.UnaryOperator;

public enum Direction {
  LEFT(point -> new Point<>(point.getX() - 1, point.getY())),
  RIGHT(point -> new Point<>(point.getX() + 1, point.getY())),
  UP(point -> new Point<>(point.getX(), point.getY() - 1)),
  DOWN(point -> new Point<>(point.getX(), point.getY() + 1));

  private final UnaryOperator<Point<Integer>> shiftPoint;

  Direction(UnaryOperator<Point<Integer>> shiftPoint) {
    this.shiftPoint = shiftPoint;
  }

  public boolean isOpposite(Direction directionToCompare) {
    return this == Direction.DOWN && directionToCompare == Direction.UP
        || this == Direction.UP && directionToCompare == Direction.DOWN
        || this == Direction.RIGHT && directionToCompare == Direction.LEFT
        || this == Direction.LEFT && directionToCompare == Direction.RIGHT;
  }

  public Point<Integer> nextPoint(Point<Integer> intPoint) {
    return this.shiftPoint.apply(intPoint);
  }
}
