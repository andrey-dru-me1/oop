package ru.nsu.fit.oop.melnikov.game.snake.snake.model;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.exceptions.NoPlaceForAppleException;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.exceptions.crash.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.exceptions.crash.SnakeInWallException;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake.SnakeNode;

class SnakeTest {

  private static final int SIZE = 7;
  private final Snake snake;
  private final Field field;

  SnakeTest() throws SnakeInSnakeException {

    FieldCell[][] fieldCells = new FieldCell[SIZE][SIZE];

    for (int i = 1; i < SIZE - 1; i++) {
      fieldCells[0][i] = new Wall(0, i);
      fieldCells[i][0] = new Wall(i, 0);

      fieldCells[SIZE - 1][i] = new Wall(SIZE - 1, i);
      fieldCells[i][SIZE - 1] = new Wall(i, SIZE - 1);

      for (int j = 1; j < SIZE - 1; j++) {
        fieldCells[i][j] = new EmptyFieldCell(i, j);
      }
    }

    field = new Field(fieldCells);

    List<SnakeNode> nodes = new LinkedList<>();
    for (int i = 1; i <= 3; i++) {
      if(field.getCell(i, 1) instanceof EmptyFieldCell emptyFieldCell) {
        nodes.add(new SnakeNode(emptyFieldCell));
      }
    }

    snake = new Snake(field, nodes);

  }

  @Test
  void testApple() {
    for (int i = 0; i < (SIZE - 2) * (SIZE - 2) - 3; i++) {
      Assertions.assertDoesNotThrow(field::generateApple);
    }
    Assertions.assertThrowsExactly(NoPlaceForAppleException.class, field::generateApple);

    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);
    snake.setDirection(Direction.DOWN);
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);

    Assertions.assertEquals(9, snake.size());
    Assertions.assertEquals(4 * 4, field.applesCount());

  }

  @Test
  void testField() {
    snake.setDirection(Direction.UP);
    Assertions.assertThrowsExactly(SnakeInWallException.class, snake::move);

    Assertions.assertEquals(1, snake.getSnakeHead().cell().getPoint().y());

    snake.setDirection(Direction.RIGHT);
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertThrowsExactly(SnakeInWallException.class, snake::move);

    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().x());
  }

  @Test
  void testSnakeInSnakeStartPlace() {
    snake.increaseSize();
    snake.increaseSize();
    snake.increaseSize();

    snake.setDirection(Direction.DOWN);
    Assertions.assertDoesNotThrow(snake::move);
    snake.setDirection(Direction.LEFT);
    Assertions.assertDoesNotThrow(snake::move);
    snake.setDirection(Direction.UP);
    Assertions.assertThrowsExactly(SnakeInSnakeException.class, snake::move);

  }

  @Test
  void testSnakeInSnakeNewPlace() {
    snake.increaseSize();
    snake.increaseSize();
    snake.increaseSize();

    snake.setDirection(Direction.DOWN);
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);
    snake.setDirection(Direction.LEFT);
    Assertions.assertDoesNotThrow(snake::move);
    snake.setDirection(Direction.UP);
    Assertions.assertDoesNotThrow(snake::move);
    snake.setDirection(Direction.RIGHT);
    Assertions.assertThrowsExactly(SnakeInSnakeException.class, snake::move);

  }

  @Test
  void testSnake() {

    Assertions.assertEquals(3, snake.getSnakeHead().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeHead().cell().getPoint().y());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().y());

    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);

    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeHead().cell().getPoint().y());
    Assertions.assertEquals(3, snake.getSnakeTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().y());

    snake.setDirection(Direction.DOWN);
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);

    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().x());
    Assertions.assertEquals(3, snake.getSnakeHead().cell().getPoint().y());
    Assertions.assertEquals(5, snake.getSnakeTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().y());

    snake.increaseSize();
    snake.increaseSize();
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);

    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().x());
    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().y());
    Assertions.assertEquals(5, snake.getSnakeTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().y());
  }

}