package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.NoPlaceForAppleException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeCrashedException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInWallException;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Wall;

public class Snake {

  /** Snake nodes, where 0 is a tail and last element is a head. */
  private final List<SnakeNode> nodes;

  private final Field field;
  private Direction direction;
  private int sizeToIncrease;
  /** Creates new snake with 3 nodes. */
  public Snake(Field field, List<SnakeNode> snakeNodes) throws SnakeInSnakeException {

    for (SnakeNode snakeNode : snakeNodes) {
      if (snakeNode.cell().getSnake().isPresent()) {
        throw new SnakeInSnakeException();
      }
      snakeNode.cell().putSnake(this);
    }

    this.field = field;
    this.nodes = snakeNodes;
    this.direction = Direction.RIGHT;
    this.sizeToIncrease = 0;
  }

  public Field getField() {
    return field;
  }

  public List<SnakeNode> getNodes() {
    return nodes;
  }

  public void move() throws SnakeCrashedException, NoPlaceForAppleException {
    appendHead();
    if (sizeToIncrease > 0) {
      sizeToIncrease--;
      return;
    }
    removeTail();
  }

  public void move(Direction direction) throws SnakeCrashedException, NoPlaceForAppleException {
    this.setDirection(direction);
    this.move();
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void increaseSize() {
    sizeToIncrease++;
  }

  public SnakeNode getHead() {
    return nodes.get(nodes.size() - 1);
  }

  public SnakeNode getTail() {
    return nodes.get(0);
  }

  public int size() {
    return nodes.size();
  }

  protected void appendHead() throws SnakeCrashedException, NoPlaceForAppleException {
    FieldCell newHeadCell = field.getCell(direction.nextPoint(this.getHead().cell().getPoint()));
    if (newHeadCell instanceof EmptyFieldCell emptyCell) {

      if (emptyCell.getSnake().isPresent()) {
        throw new SnakeInSnakeException();
      }

      emptyCell.putSnake(this);
      nodes.add(new SnakeNode(emptyCell));

      if (emptyCell.hasApple()) {
        this.increaseSize();
        field.eatApple(emptyCell);
        field.generateApple(); // TODO: make a listener that generates apple when it is eaten
      }
    } else if (newHeadCell instanceof Wall) {
      throw new SnakeInWallException();
    }
  }

  protected SnakeNode removeTail() {
    SnakeNode snakeNode = nodes.remove(0);
    snakeNode.cell().moveSnake();
    return snakeNode;
  }
}
