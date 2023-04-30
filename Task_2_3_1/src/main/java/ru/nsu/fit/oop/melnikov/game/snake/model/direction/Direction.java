package ru.nsu.fit.oop.melnikov.game.snake.model.direction;

import java.util.function.UnaryOperator;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.IntPoint;

public enum Direction {
  LEFT(point -> new IntPoint(point.getX() - 1, point.getY())),
  RIGHT(point -> new IntPoint(point.getX() + 1, point.getY())),
  UP(point -> new IntPoint(point.getX(), point.getY() - 1)),
  DOWN(point -> new IntPoint(point.getX(), point.getY() + 1));

  private final UnaryOperator<IntPoint> shiftPoint;

  Direction(UnaryOperator<IntPoint> shiftPoint) {
    this.shiftPoint = shiftPoint;
  }

  public IntPoint nextPoint(IntPoint intPoint) {
    return this.shiftPoint.apply(intPoint);
  }

}
