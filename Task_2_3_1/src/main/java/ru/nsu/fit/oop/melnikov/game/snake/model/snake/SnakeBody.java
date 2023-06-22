package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces.Destroyable;

class SnakeBody implements Destroyable {

  /** Snake nodes, where 0 is a tail and last element is a head. */
  private final List<SnakePoint> nodes;

  /** Creates new snake with 3 nodes. */
  public SnakeBody(List<SnakePoint> snakeIntPoints) {
    this.nodes = snakeIntPoints;
  }

  public List<SnakePoint> getNodes() {
    return nodes;
  }

  public SnakePoint getHeadPoint() {
    return nodes.get(nodes.size() - 1);
  }

  public SnakePoint getTailPoint() {
    return nodes.get(0);
  }

  public int size() {
    return nodes.size();
  }

  /** Appends head to a next cell according to snake direction. */
  public void appendHead(SnakePoint newHeadPoint) {
    nodes.add(newHeadPoint);
  }

  public SnakePoint removeTail() {
    return nodes.remove(0);
  }

  @Override
  public void destroy() {
    nodes.clear();
  }
}
