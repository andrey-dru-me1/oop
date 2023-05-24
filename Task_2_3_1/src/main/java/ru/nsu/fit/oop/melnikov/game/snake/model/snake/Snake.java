package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.LinkedList;
import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

/** Snake consists from cells. */
public class Snake {

  /** Snake nodes, where 0 is a tail and last element is a head. */
  private final List<Cell> nodes;

  private final Field field;
  private int score;
  private Direction direction;
  private int sizeToIncrease;
  private boolean isDestroyed;
  /** Creates new snake with 3 nodes. */
  public Snake(Field field, List<Point<Integer>> snakeIntPoints) {

    this.nodes = new LinkedList<>();
    this.field = field;

    for (Point<Integer> intPoint : snakeIntPoints) {
      Cell cell = field.getCell(intPoint);
      nodes.add(cell);
      cell.add(new SnakeNode(this));
    }

    this.direction = Direction.RIGHT;
    this.sizeToIncrease = 0;
    isDestroyed = false;
    score = 0;
  }

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

  public List<Cell> getNodes() {
    return nodes;
  }

  /**
   * Appends head and if there is no need to increase its size removes tail. Doesn't change
   * direction.
   */
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
  public void move(Direction direction) {
    this.setDirection(direction);
    this.move();
  }

  public void increaseSize(int additionalSize) {
    sizeToIncrease += additionalSize;
  }

  public void increaseScore(int additionalPoints) {
    score += additionalPoints;
  }

  public Cell getHeadCell() {
    return nodes.get(nodes.size() - 1);
  }

  public Cell getTailCell() {
    return nodes.get(0);
  }

  public int size() {
    return nodes.size();
  }

  /** Appends head to a next cell according to snake direction. */
  protected void appendHead() {
    Point<Integer> nextPoint = calculateNextPoint();
    Cell newHeadCell = field.getCell(nextPoint);
    nodes.add(newHeadCell);
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
    if(nodes.isEmpty()) {
      return;
    }
    Cell tailCell = nodes.remove(0);
    tailCell.remove(SnakeNode.class);
  }

  public void destroy() {
    isDestroyed = true;
    for (Cell cell : nodes) {
      cell.remove(SnakeNode.class);
    }
    nodes.clear();
  }
}
