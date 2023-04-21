package ru.nsu.fit.oop.melnikov.game.snake.model.field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.model.ModelInit;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;

class FieldTest extends ModelInit {

  public FieldTest() {
    super();
  }

  @Test
  void testApple() {
    for (int i = 0; i < (SIZE - 2) * (SIZE - 2) - 3; i++) {
      Assertions.assertDoesNotThrow(field::generateApple);
    }
    Assertions.assertFalse(field.isNoPlaceForApple());
    field.generateApple();
    Assertions.assertTrue(field.isNoPlaceForApple());

    snake.move();
    snake.move();
    snake.move(Direction.DOWN);
    snake.move();
    snake.move();
    snake.move();

    Assertions.assertEquals(9, snake.size());
    Assertions.assertEquals(4 * 4, field.applesCount());
  }

  @Test
  void testField() {
    snake.setDirection(Direction.UP);
    Assertions.assertFalse(snake.isDestroyed());
    Assertions.assertDoesNotThrow(() -> snake.move());
    Assertions.assertTrue(snake.isDestroyed());
  }
}
