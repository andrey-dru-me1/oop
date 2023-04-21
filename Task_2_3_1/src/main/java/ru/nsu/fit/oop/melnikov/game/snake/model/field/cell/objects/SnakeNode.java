package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class SnakeNode implements CellObject {

  private final Snake snake;

  public SnakeNode(Snake snake) {
    this.snake = snake;
  }

  public Snake getSnake() {
    return snake;
  }

  @Override
  public int getPriority() {
    return 50;
  }

  @Override
  public void onAnotherCellObjectAppearance(CellObject anotherCellObject) {
    snake.destroy();
  }
}
