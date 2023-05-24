package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Destroyable;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Increasing;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Scoring;

/** Snake consists from cells. */
public class Snake extends MovableSnake implements Destroyable, Increasing, Scoring {
  private int score;
  private boolean isDestroyed;
  /** Creates new snake with 3 nodes. */
  public Snake(Field field, List<SnakePoint> snakeIntPoints) {
    super(field, snakeIntPoints);

    isDestroyed = false;
    score = 0;
  }

  @Override
  public int getScore() {
    return score;
  }

  public boolean isDestroyed() {
    return isDestroyed;
  }

  @Override
  public void increaseScore(int additionalPoints) {
    score += additionalPoints;
  }

  @Override
  public void destroy() {
    isDestroyed = true;
    for (SnakePoint snakePoint : body.getNodes()) {
      field.getCell(snakePoint).remove(SnakeNode.class);
    }
    body.destroy();
  }
}
