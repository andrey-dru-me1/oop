package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

/**
 * Snake node can represent whether snake head and other snake body: model doesn't see any
 * differences.
 *
 * @param snake
 */
public record SnakeNode(Snake snake) implements CellObject {

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
    snake.destroy();
  }
}
