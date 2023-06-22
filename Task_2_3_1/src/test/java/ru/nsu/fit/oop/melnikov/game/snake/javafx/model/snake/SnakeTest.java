package ru.nsu.fit.oop.melnikov.game.snake.javafx.model.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.model.ModelInit;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;

class SnakeTest extends ModelInit {

  public SnakeTest() {
    super();
  }

  @Test
  void testSnakeInSnakeStartPlace() {
    snake.increaseSize(3);

    Assertions.assertDoesNotThrow(() -> snake.move(Direction.DOWN));
    Assertions.assertDoesNotThrow(() -> snake.move(Direction.LEFT));
    Assertions.assertFalse(snake.isDestroyed());
    Assertions.assertDoesNotThrow(() -> snake.move(Direction.UP));
    Assertions.assertTrue(snake.isDestroyed());
  }

  @Test
  void testSnakeInSnakeNewPlace() {
    snake.increaseSize(3);

    Assertions.assertDoesNotThrow(() -> snake.move(Direction.DOWN));
    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertDoesNotThrow(() -> snake.move(Direction.LEFT));
    Assertions.assertDoesNotThrow(() -> snake.move(Direction.UP));
    Assertions.assertFalse(snake.isDestroyed());
    Assertions.assertDoesNotThrow(() -> snake.move(Direction.RIGHT));
    Assertions.assertTrue(snake.isDestroyed());
  }

  @Test
  void testSnake() {

    Assertions.assertEquals(3, snake.getHeadCell().getX());
    Assertions.assertEquals(1, snake.getHeadCell().getY());
    Assertions.assertEquals(1, snake.getTailCell().getX());
    Assertions.assertEquals(1, snake.getTailCell().getY());

    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertDoesNotThrow(() -> snake.move());

    Assertions.assertEquals(5, snake.getHeadCell().getX());
    Assertions.assertEquals(1, snake.getHeadCell().getY());
    Assertions.assertEquals(3, snake.getTailCell().getX());
    Assertions.assertEquals(1, snake.getTailCell().getY());

    Assertions.assertDoesNotThrow(() -> snake.move(Direction.DOWN));
    Assertions.assertDoesNotThrow(() -> snake.move());

    Assertions.assertEquals(5, snake.getHeadCell().getX());
    Assertions.assertEquals(3, snake.getHeadCell().getY());
    Assertions.assertEquals(5, snake.getTailCell().getX());
    Assertions.assertEquals(1, snake.getTailCell().getY());

    snake.increaseSize(2);
    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertDoesNotThrow(() -> snake.move());

    Assertions.assertEquals(5, snake.getHeadCell().getX());
    Assertions.assertEquals(5, snake.getHeadCell().getY());
    Assertions.assertEquals(5, snake.getTailCell().getX());
    Assertions.assertEquals(1, snake.getTailCell().getY());
  }
}
