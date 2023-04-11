package ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell;

import ru.nsu.fit.oop.melnikov.game.snake.snake.model.point.Point;

public class FieldCell {

  private final Point point;

  protected FieldCell(int x, int y) {
    point = new Point(x, y);
  }

  protected FieldCell(Point point) {
    this.point = point;
  }

  public Point getPoint() {
    return point;
  }
}
