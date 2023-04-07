package ru.nsu.fit.oop.melnikov.game.snake.snake.model;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake.SnakeNode;

class SnakeTest {

  private static final int SIZE = 7;
  private final Snake snake;

  SnakeTest() {

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

    Field field = new Field(fieldCells);

    List<SnakeNode> nodes = new LinkedList<>(
        List.of(
            new SnakeNode(1, 1),
            new SnakeNode(2, 1),
            new SnakeNode(3, 1)
        )
    );

    snake = new Snake(field, nodes);

  }

  @Test
  void testField() {
    snake.setDirection(Direction.UP);
    snake.move();

    Assertions.assertEquals(2, snake.size());
    Assertions.assertEquals(1, snake.getSnakeHead().y());

    snake.setDirection(Direction.RIGHT);
    snake.move();
    snake.move();
    snake.move();

    Assertions.assertEquals(1, snake.size());
  }

  @Test
  void testSnake() {

    Assertions.assertEquals(3, snake.getSnakeHead().x());
    Assertions.assertEquals(1, snake.getSnakeHead().y());
    Assertions.assertEquals(1, snake.getSnakeTail().x());
    Assertions.assertEquals(1, snake.getSnakeTail().y());

    snake.move();
    snake.move();

    Assertions.assertEquals(5, snake.getSnakeHead().x());
    Assertions.assertEquals(1, snake.getSnakeHead().y());
    Assertions.assertEquals(3, snake.getSnakeTail().x());
    Assertions.assertEquals(1, snake.getSnakeTail().y());

    snake.setDirection(Direction.DOWN);
    snake.move();
    snake.move();

    Assertions.assertEquals(5, snake.getSnakeHead().x());
    Assertions.assertEquals(3, snake.getSnakeHead().y());
    Assertions.assertEquals(5, snake.getSnakeTail().x());
    Assertions.assertEquals(1, snake.getSnakeTail().y());

    snake.increaseSize();
    snake.increaseSize();
    snake.move();
    snake.move();

    Assertions.assertEquals(5, snake.getSnakeHead().x());
    Assertions.assertEquals(5, snake.getSnakeHead().y());
    Assertions.assertEquals(5, snake.getSnakeTail().x());
    Assertions.assertEquals(1, snake.getSnakeTail().y());
  }

}