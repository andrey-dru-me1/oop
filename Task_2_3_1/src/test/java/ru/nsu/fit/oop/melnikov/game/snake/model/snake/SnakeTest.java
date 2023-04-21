package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.model.ModelInit;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;

class SnakeTest extends ModelInit {

  public SnakeTest() {
    super();
  }

  @Test
  void testSnakeInSnakeStartPlace() {
    snake.increaseSize();
    snake.increaseSize();
    snake.increaseSize();

    Assertions.assertDoesNotThrow(() -> snake.move(Direction.DOWN));
    Assertions.assertDoesNotThrow(() -> snake.move(Direction.LEFT));
    Assertions.assertFalse(snake.isDestroyed());
    Assertions.assertDoesNotThrow(() -> snake.move(Direction.UP));
    Assertions.assertTrue(snake.isDestroyed());
  }

  @Test
  void testSnakeInSnakeNewPlace() {
    snake.increaseSize();
    snake.increaseSize();
    snake.increaseSize();

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

    Assertions.assertEquals(3, snake.getHeadCell().getPoint().x());
    Assertions.assertEquals(1, snake.getHeadCell().getPoint().y());
    Assertions.assertEquals(1, snake.getTailCell().getPoint().x());
    Assertions.assertEquals(1, snake.getTailCell().getPoint().y());

    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertDoesNotThrow(() -> snake.move());

    Assertions.assertEquals(5, snake.getHeadCell().getPoint().x());
    Assertions.assertEquals(1, snake.getHeadCell().getPoint().y());
    Assertions.assertEquals(3, snake.getTailCell().getPoint().x());
    Assertions.assertEquals(1, snake.getTailCell().getPoint().y());

    Assertions.assertDoesNotThrow(() -> snake.move(Direction.DOWN));
    Assertions.assertDoesNotThrow(() -> snake.move());

    Assertions.assertEquals(5, snake.getHeadCell().getPoint().x());
    Assertions.assertEquals(3, snake.getHeadCell().getPoint().y());
    Assertions.assertEquals(5, snake.getTailCell().getPoint().x());
    Assertions.assertEquals(1, snake.getTailCell().getPoint().y());

    snake.increaseSize();
    snake.increaseSize();
    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertDoesNotThrow(() -> snake.move());

    Assertions.assertEquals(5, snake.getHeadCell().getPoint().x());
    Assertions.assertEquals(5, snake.getHeadCell().getPoint().y());
    Assertions.assertEquals(5, snake.getTailCell().getPoint().x());
    Assertions.assertEquals(1, snake.getTailCell().getPoint().y());
  }
}
