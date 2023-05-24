package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public class SnakePoint extends Point<Integer> {
  public SnakePoint(Integer x, Integer y) {
    super(x, y);
  }

  public SnakePoint(Point<Integer> point) {
    this(point.getX(), point.getY());
  }
}
