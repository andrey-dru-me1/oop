package ru.nsu.fit.oop.melnikov.game.snake.snake.model.direction;

import java.util.function.UnaryOperator;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake.SnakeNode;

public enum Direction {
  LEFT(snakeNode -> new SnakeNode(snakeNode.x() - 1, snakeNode.y())),
  RIGHT(snakeNode -> new SnakeNode(snakeNode.x() + 1, snakeNode.y())),
  UP(snakeNode -> new SnakeNode(snakeNode.x(), snakeNode.y() - 1)),
  DOWN(snakeNode -> new SnakeNode(snakeNode.x(), snakeNode.y() + 1));

  private final UnaryOperator<SnakeNode> shiftPoint;

  Direction(UnaryOperator<SnakeNode> shiftPoint) {
    this.shiftPoint = shiftPoint;
  }

  public SnakeNode shiftPoint(SnakeNode snakeNode) {
    return this.shiftPoint.apply(snakeNode);
  }

}
