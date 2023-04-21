package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

public class Wall implements CellObject {
  @Override
  public int getPriority() {
    return 100;
  }

  @Override
  public void onAnotherCellObjectAppearance(CellObject anotherCellObject) {
    if (anotherCellObject instanceof SnakeNode snakeNode) {
      snakeNode.getSnake().destroy();
    }
  }
}
