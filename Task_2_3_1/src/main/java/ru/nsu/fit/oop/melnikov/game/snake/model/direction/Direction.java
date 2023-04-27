package ru.nsu.fit.oop.melnikov.game.snake.model.direction;

import java.util.function.UnaryOperator;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public enum Direction {
  LEFT(point -> new Point(point.getX() - 1, point.getY())),
  RIGHT(point -> new Point(point.getX() + 1, point.getY())),
  UP(point -> new Point(point.getX(), point.getY() - 1)),
  DOWN(point -> new Point(point.getX(), point.getY() + 1));

  private final UnaryOperator<Point> shiftPoint;

  Direction(UnaryOperator<Point> shiftPoint) {
    this.shiftPoint = shiftPoint;
  }

  public Point nextPoint(Point point) {
    return this.shiftPoint.apply(point);
  }

}
