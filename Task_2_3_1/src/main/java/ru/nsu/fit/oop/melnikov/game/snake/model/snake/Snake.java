package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Destroyable;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Increasing;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Movable;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Scoring;

/** Snake consists from cells. */
public class Snake implements Destroyable, Increasing, Movable, Scoring {

  /** Snake nodes, where 0 is a tail and last element is a head. */
  private final SnakeBody body;

  private final Field field;
  private int score;
  private Direction direction;
  private int sizeToIncrease;
  private boolean isDestroyed;
  /** Creates new snake with 3 nodes. */
  public Snake(Field field, List<SnakePoint> snakeIntPoints) {

    this.body = new SnakeBody(snakeIntPoints);
    this.field = field;

    for (SnakePoint intPoint : snakeIntPoints) {
      field.getCell(intPoint).add(new SnakeNode(this));
    }

    this.direction = Direction.RIGHT;
    this.sizeToIncrease = 0;
    isDestroyed = false;
    score = 0;
  }

  @Override
  public int getScore() {
    return score;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public boolean isDestroyed() {
    return isDestroyed;
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

  /**
   * Appends head and if there is no need to increase its size removes tail. Doesn't change
   * direction.
   */
  @Override
  public void move() {
    appendHead();
    if (sizeToIncrease > 0) {
      sizeToIncrease--;
      return;
    }
    removeTail();
  }

  /**
   * Changes snake move direction and moves like {@link Snake#move()}
   *
   * @param direction direction to move to
   */
  @Override
  public void move(Direction direction) {
    this.setDirection(direction);
    this.move();
  }

  @Override
  public void increaseSize(int additionalSize) {
    sizeToIncrease += additionalSize;
  }

  @Override
  public void increaseScore(int additionalPoints) {
    score += additionalPoints;
  }

  public Cell getHeadCell() {
    return field.getCell(body.getHeadPoint());
  }

  public Cell getTailCell() {
    return field.getCell(body.getTailPoint());
  }

  /** Appends head to a next cell according to snake direction. */
  protected void appendHead() {
    Point<Integer> nextPoint = calculateNextPoint();
    Cell newHeadCell = field.getCell(nextPoint);
    body.appendHead(new SnakePoint(nextPoint));
    newHeadCell.add(new SnakeNode(this));
  }

  /**
   * Returns next point according to direction if point doesn't exit field's bounds and first point
   * on a same line/row from an opposite side.
   *
   * @return next point
   */
  private Point<Integer> calculateNextPoint() {
    Point<Integer> nextPoint = direction.nextPoint(this.getHeadCell());

    if (nextPoint.getX() >= field.getWidth()) {
      nextPoint = new Point<>(0, nextPoint.getY());
    } else if (nextPoint.getX() < 0) {
      nextPoint = new Point<>(field.getWidth() - 1, nextPoint.getY());
    } else if (nextPoint.getY() >= field.getHeight()) {
      nextPoint = new Point<>(nextPoint.getX(), 0);
    } else if (nextPoint.getY() < 0) {
      nextPoint = new Point<>(nextPoint.getX(), field.getHeight() - 1);
    }
    return nextPoint;
  }

  protected void removeTail() {
    if(body.getNodes().isEmpty()) {
      return;
    }
    Cell tailCell = field.getCell(body.removeTail());
    tailCell.remove(SnakeNode.class);
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
