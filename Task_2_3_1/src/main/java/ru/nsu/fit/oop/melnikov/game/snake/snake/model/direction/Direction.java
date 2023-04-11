package ru.nsu.fit.oop.melnikov.game.snake.snake.model.direction;

import java.util.function.UnaryOperator;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.point.Point;

public enum Direction {
  LEFT(point -> new Point(point.x() - 1, point.y())),
  RIGHT(point -> new Point(point.x() + 1, point.y())),
  UP(point -> new Point(point.x(), point.y() - 1)),
  DOWN(point -> new Point(point.x(), point.y() + 1));

  private final UnaryOperator<Point> shiftPoint;

  Direction(UnaryOperator<Point> shiftPoint) {
    this.shiftPoint = shiftPoint;
  }

  public Point nextPoint(Point point) {
    return this.shiftPoint.apply(point);
  }

}
