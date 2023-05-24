package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Destroyable;

/** Wall is an object that fills all the cell, so snakes can't go through it. */
public class Wall implements CellObject {
  /**
   * Highest priority among default cell objects.
   *
   * @return object priority
   */
  @Override
  public int getPriority() {
    return 100;
  }

  /**
   * Destroys every snake happens to appear on a cell with a wall.
   *
   * @param anotherCellObject new object on current cell
   */
  @Override
  public void onAnotherCellObjectAppearance(CellObject anotherCellObject) {
    if (anotherCellObject instanceof SnakeNode snakeNode
        && (snakeNode.snake() instanceof Destroyable destroyableSnake)) {
        destroyableSnake.destroy();

    }
  }
}
