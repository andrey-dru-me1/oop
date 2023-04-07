package ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell;

public class FieldCell {

  private final int x;
  private final int y;

  protected FieldCell(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
