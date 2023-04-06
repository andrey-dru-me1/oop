package ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.direction.Direction;

class SnakeTest {

  @Test
  void test() {
    Snake snake = new Snake();

    Assertions.assertTrue(
        snake.getSnakeHead().x() == 2
            && snake.getSnakeHead().y() == 0
            && snake.getSnakeTail().x() == 0
            && snake.getSnakeTail().y() == 0
    );

    snake.move();
    snake.move();

    Assertions.assertTrue(
        snake.getSnakeHead().x() == 4
            && snake.getSnakeHead().y() == 0
            && snake.getSnakeTail().x() == 2
            && snake.getSnakeTail().y() == 0
    );

    snake.setDirection(Direction.DOWN);
    snake.move();
    snake.move();

    Assertions.assertTrue(
        snake.getSnakeHead().x() == 4
            && snake.getSnakeHead().y() == 2
            && snake.getSnakeTail().x() == 4
            && snake.getSnakeTail().y() == 0
    );

    snake.increaseSize();
    snake.increaseSize();
    snake.move();
    snake.move();

    Assertions.assertTrue(
        snake.getSnakeHead().x() == 4
            && snake.getSnakeHead().y() == 4
            && snake.getSnakeTail().x() == 4
            && snake.getSnakeTail().y() == 0
    );

  }

}