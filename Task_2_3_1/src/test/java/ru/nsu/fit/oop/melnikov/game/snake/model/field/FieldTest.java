package ru.nsu.fit.oop.melnikov.game.snake.model.field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.model.ModelInit;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.NoPlaceForAppleException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInWallException;

class FieldTest extends ModelInit {

  public FieldTest() throws SnakeInSnakeException {
    super();
  }

  @Test
  void testApple() {
    for (int i = 0; i < (SIZE - 2) * (SIZE - 2) - 3; i++) {
      Assertions.assertDoesNotThrow(field::generateApple);
    }
    Assertions.assertThrowsExactly(NoPlaceForAppleException.class, field::generateApple);

    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertDoesNotThrow(() -> snake.move(Direction.DOWN));
    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertDoesNotThrow(() -> snake.move());

    Assertions.assertEquals(9, snake.size());
    Assertions.assertEquals(4 * 4, field.applesCount());

  }

  @Test
  void testField() {
    snake.setDirection(Direction.UP);
    Assertions.assertThrowsExactly(SnakeInWallException.class, snake::move);

    Assertions.assertEquals(1, snake.getHead().cell().getPoint().y());

    Assertions.assertDoesNotThrow(() -> snake.move(Direction.RIGHT));
    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertThrowsExactly(SnakeInWallException.class, snake::move);

    Assertions.assertEquals(5, snake.getHead().cell().getPoint().x());
  }

}
