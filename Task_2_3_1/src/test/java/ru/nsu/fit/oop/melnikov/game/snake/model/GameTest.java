package ru.nsu.fit.oop.melnikov.game.snake.model;

import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInSnakeException;

class GameTest extends ModelInit {

  public GameTest() throws SnakeInSnakeException {
    super();
  }

  @Test
  void test() {
    Game game = new Game(snake);
    game.start();
    Assertions.assertFalse(game.isCrush());
    await().atMost(1, TimeUnit.SECONDS).until(game::isCrush);
    Assertions.assertTrue(game.isCrush());
  }

}