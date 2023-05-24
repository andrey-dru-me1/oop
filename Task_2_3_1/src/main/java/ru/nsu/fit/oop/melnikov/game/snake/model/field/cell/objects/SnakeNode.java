package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Destroyable;

/**
 * Snake node can represent whether snake head and other snake body: model doesn't see any
 * differences.
 *
 * @param snake
 */
public record SnakeNode(Destroyable snake) implements CellObject, Destroyable {

  /**
   * More than apple, less than wall.
   *
   * @return object priority
   */
  @Override
  public int getPriority() {
    return 50;
  }

  /**
   * Destroys linked snake when something crashes into its node.
   *
   * @param anotherCellObject new object on current cell
   */
  @Override
  public void onAnotherCellObjectAppearance(CellObject anotherCellObject) {
    destroy();
  }

  @Override
  public void destroy() {
    snake.destroy();
  }

  @Override
  public boolean isDestroyed() {
    return false;
  }
}
