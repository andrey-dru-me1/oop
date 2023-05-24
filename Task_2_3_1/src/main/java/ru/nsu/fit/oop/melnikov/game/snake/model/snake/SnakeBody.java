package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Destroyable;

class SnakeBody implements Destroyable {

  /** Snake nodes, where 0 is a tail and last element is a head. */
  private final List<Point<Integer>> nodes;

  /** Creates new snake with 3 nodes. */
  public SnakeBody(List<Point<Integer>> snakeIntPoints) {
    this.nodes = snakeIntPoints;
  }

  public List<Point<Integer>> getNodes() {
    return nodes;
  }

  public Point<Integer> getHeadPoint() {
    return nodes.get(nodes.size() - 1);
  }

  public Point<Integer> getTailPoint() {
    return nodes.get(0);
  }

  public int size() {
    return nodes.size();
  }

  /** Appends head to a next cell according to snake direction. */
  public void appendHead(Point<Integer> newHeadPoint) {
    nodes.add(newHeadPoint);
  }

  public Point<Integer> removeTail() {
    return nodes.remove(0);
  }

  @Override
  public void destroy() {
    nodes.clear();
  }
}
