package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Increasing;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Movable;

abstract class MovableSnake extends AbstractSnake implements Movable, Increasing {
  private Direction direction;
  private int sizeToIncrease;

  protected MovableSnake(Field field, List<SnakePoint> snakeIntPoints) {
    super(field, snakeIntPoints);

    this.direction = Direction.RIGHT;
    this.sizeToIncrease = 0;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  @Override
  public void increaseSize(int additionalSize) {
    sizeToIncrease += additionalSize;
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

  /** Appends head to a next cell according to snake direction. */
  protected void appendHead() {
    Point<Integer> nextPoint = calculateNextPoint();
    body.appendHead(new SnakePoint(nextPoint));
    Cell newHeadCell = field.getCell(nextPoint);
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
    if (body.getNodes().isEmpty()) {
      return;
    }
    Cell tailCell = field.getCell(body.removeTail());
    tailCell.remove(SnakeNode.class);
  }
}
