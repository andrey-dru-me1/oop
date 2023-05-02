package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public record SnakeNode(Snake snake) implements CellObject {

  @Override
  public int getPriority() {
    return 50;
  }

  @Override
  public void onAnotherCellObjectAppearance(CellObject anotherCellObject) {
    snake.destroy();
  }
}
