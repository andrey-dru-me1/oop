package ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.exceptions.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.exceptions.SnakeInWallException;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.Wall;

public class Snake {

  /**
   * Snake nodes, where 0 is a tail and last element is a head.
   */
  private final List<SnakeNode> nodes;
  private final Field field;
  private Direction direction;
  private int sizeToIncrease;

  /**
   * Creates new snake with 3 nodes.
   */
  public Snake(Field field, List<SnakeNode> snakeNodes) throws SnakeInSnakeException {

    for (SnakeNode snakeNode : snakeNodes) {
      if (snakeNode.cell().getSnake().isPresent()) {
        throw new SnakeInSnakeException();
      }
    }

    this.field = field;
    this.nodes = snakeNodes;
    this.direction = Direction.RIGHT;
    this.sizeToIncrease = 0;
  }

  public void move() throws SnakeInSnakeException, SnakeInWallException {
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

  public int size() {
    return nodes.size();
  }

  private void appendHead() throws SnakeInSnakeException, SnakeInWallException {
    FieldCell newHeadCell = field.getCell(
        direction.nextPoint(this.getSnakeHead().cell().getPoint())
    );
    if (newHeadCell instanceof EmptyFieldCell emptyCell) {

      if(emptyCell.getSnake().isPresent()) {
        throw new SnakeInSnakeException();
      }

      if (emptyCell.hasApple()) {
        this.increaseSize();
        emptyCell.eatApple();
        field.generateApple();  //TODO: make a listener that generates apple when it is eaten
      }

      emptyCell.putSnake(this);
      nodes.add(new SnakeNode(emptyCell));
    }
    else if(newHeadCell instanceof Wall) {
      throw new SnakeInWallException();
    }
  }

  private void removeTail() {
    SnakeNode snakeNode = nodes.remove(0);
    snakeNode.cell().moveSnake();
  }

}
