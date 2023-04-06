package ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake;

import java.util.LinkedList;
import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.direction.Direction;

public class Snake {

  /**
   * Snake nodes, where 0 is a tail and last element is a head.
   */
  private final List<SnakeNode> nodes;
  private Direction direction;
  private int sizeToIncrease;

  /**
   * Creates new snake with 3 nodes.
   */
  public Snake() {
    this.nodes = new LinkedList<>();
    this.direction = Direction.RIGHT;
    this.sizeToIncrease = 0;

    for (int i = 0; i < 3; i++) {
      this.nodes.add(new SnakeNode(i, 0));
    }

  }

  public void move() {
    appendHead();
    if (sizeToIncrease > 0) {
      sizeToIncrease--;
      return;
    }
    removeTail();
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void increaseSize() {
    sizeToIncrease++;
  }

  public SnakeNode getSnakeHead() {
    return nodes.get(nodes.size() - 1);
  }

  public SnakeNode getSnakeTail() {
    return nodes.get(0);
  }

  private void appendHead() {
    nodes.add(direction.shiftPoint(this.getSnakeHead()));
  }

  private void removeTail() {
    nodes.remove(getSnakeTail());
  }

}
