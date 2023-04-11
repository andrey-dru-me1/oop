package ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.ModelTest;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.exceptions.crash.SnakeInSnakeException;

public class SnakeTest extends ModelTest {

  public SnakeTest() throws SnakeInSnakeException {
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

    Assertions.assertEquals(3, snake.getHead().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getHead().cell().getPoint().y());
    Assertions.assertEquals(1, snake.getTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getTail().cell().getPoint().y());

    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);

    Assertions.assertEquals(5, snake.getHead().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getHead().cell().getPoint().y());
    Assertions.assertEquals(3, snake.getTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getTail().cell().getPoint().y());

    snake.setDirection(Direction.DOWN);
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);

    Assertions.assertEquals(5, snake.getHead().cell().getPoint().x());
    Assertions.assertEquals(3, snake.getHead().cell().getPoint().y());
    Assertions.assertEquals(5, snake.getTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getTail().cell().getPoint().y());

    snake.increaseSize();
    snake.increaseSize();
    Assertions.assertDoesNotThrow(snake::move);
    Assertions.assertDoesNotThrow(snake::move);

    Assertions.assertEquals(5, snake.getHead().cell().getPoint().x());
    Assertions.assertEquals(5, snake.getHead().cell().getPoint().y());
    Assertions.assertEquals(5, snake.getTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getTail().cell().getPoint().y());
  }
}
