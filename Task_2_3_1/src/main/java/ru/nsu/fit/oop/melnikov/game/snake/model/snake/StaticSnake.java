package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;

public class StaticSnake {

  protected final SnakeBody body;
  protected final Field field;

  public StaticSnake(Field field, List<SnakePoint> snakeIntPoints) {
    this.body = new SnakeBody(snakeIntPoints);
    this.field = field;
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
