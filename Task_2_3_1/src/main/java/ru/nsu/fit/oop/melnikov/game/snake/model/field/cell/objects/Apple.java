package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

public class Apple implements CellObject {

  private final Runnable onAppleEating;

  public Apple(Runnable onAppleEating) {
    this.onAppleEating = onAppleEating;
  }

  @Override
  public int getPriority() {
    return 25;
  }

  @Override
  public void onAnotherCellObjectAppearance(CellObject anotherCellObject) {
    if (anotherCellObject instanceof SnakeNode snakeNode) {
      snakeNode.getSnake().increaseSize();
      onAppleEating.run();
    }
  }

}
