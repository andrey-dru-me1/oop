package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;

public abstract class AbstractSnake {

  protected final SnakeBody body;
  protected final Field field;

  protected AbstractSnake(Field field, List<SnakePoint> snakeIntPoints) {
    this.body = new SnakeBody(snakeIntPoints);
    this.field = field;

    for (SnakePoint intPoint : snakeIntPoints) {
      field.getCell(intPoint).add(new SnakeNode(this));
    }
  }

  public Field getField() {
    return field;
  }

  public int size() {
    return body.size();
  }

  public List<SnakePoint> getNodes() {
    return body.getNodes();
  }

  public Cell getHeadCell() {
    return field.getCell(body.getHeadPoint());
  }

  public Cell getTailCell() {
    return field.getCell(body.getTailPoint());
  }
}
