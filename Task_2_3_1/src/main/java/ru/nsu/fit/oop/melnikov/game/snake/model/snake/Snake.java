package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.LinkedList;
import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public class Snake {

  /** Snake nodes, where 0 is a tail and last element is a head. */
  private final List<Cell> nodes;

  private final Field field;
  private Direction direction;
  private int sizeToIncrease;
  private boolean isDestroyed;

  /** Creates new snake with 3 nodes. */
  protected Snake(Field field, List<Point> snakePoints) {

    this.nodes = new LinkedList<>();
    this.field = field;

    for (Point point : snakePoints) {
      Cell cell = field.getCell(point);
      nodes.add(cell);
      cell.add(new SnakeNode(this));
    }

    this.direction = Direction.RIGHT;
    this.sizeToIncrease = 0;
    isDestroyed = false;
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

  public void move() {
    appendHead();
    if (sizeToIncrease > 0) {
      sizeToIncrease--;
      return;
    }
    removeTail();
  }

  public void move(Direction direction) {
    this.setDirection(direction);
    this.move();
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void increaseSize() {
    sizeToIncrease++;
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

  protected void appendHead() {
    Cell newHeadCell = field.getCell(direction.nextPoint(this.getHeadCell().getPoint()));
    nodes.add(newHeadCell);
    newHeadCell.add(new SnakeNode(this));
  }

  protected void removeTail() {
    Cell tailCell = nodes.remove(0);
    tailCell.remove(SnakeNode.class);
  }

  public void destroy() {
    isDestroyed = true;
    nodes.parallelStream().forEach(cell -> cell.remove(SnakeNode.class));
  }
}
