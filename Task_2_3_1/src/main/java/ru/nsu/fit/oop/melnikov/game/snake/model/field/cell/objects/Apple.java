package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Increasing;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Scoring;

public class Apple implements CellObject {

  private final Runnable onAppleEating;

  /**
   * Passes runnable argument {@code onAppleEating} which will be called on snake appearance.
   *
   * @param onAppleEating method specifies behaviour of game when snake eats apple
   */
  public Apple(Runnable onAppleEating) {
    this.onAppleEating = onAppleEating;
  }

  /**
   * Greater than empty cell, less than snake node.
   *
   * @return object priority
   */
  @Override
  public int getPriority() {
    return 25;
  }

  /**
   * Increases snake size and calls {@code onAppleEating()} callback method specified in {@code
   * Apple} constructor if snake node appears on cell.
   *
   * @param anotherCellObject cell object that appears on cell
   */
  @Override
  public void onAnotherCellObjectAppearance(CellObject anotherCellObject) {
    if (anotherCellObject instanceof SnakeNode snakeNode) {
      if(snakeNode.snake() instanceof Increasing increasingSnake) {
        increasingSnake.increaseSize(1);
      }
      if(snakeNode.snake() instanceof Scoring scoringSnake) {
        scoringSnake.increaseScore(1);
      }
      onAppleEating.run();
    }
  }
}