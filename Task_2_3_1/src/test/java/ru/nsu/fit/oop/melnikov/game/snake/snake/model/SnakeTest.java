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
  private final Field field;

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
  void testField() {
    snake.setDirection(Direction.UP);
    snake.move();

    Assertions.assertEquals(1, snake.getSnakeHead().cell().getPoint().y());

    snake.setDirection(Direction.RIGHT);
    for (int i = 0; i < 3; i++) {
      snake.move();
    }

    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().x());
  }

  @Test
  void testSnake() {

    Assertions.assertEquals(3, snake.getSnakeHead().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeHead().cell().getPoint().y());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().y());

    snake.move();
    snake.move();

    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeHead().cell().getPoint().y());
    Assertions.assertEquals(3, snake.getSnakeTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().y());

    snake.setDirection(Direction.DOWN);
    snake.move();
    snake.move();

    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().x());
    Assertions.assertEquals(3, snake.getSnakeHead().cell().getPoint().y());
    Assertions.assertEquals(5, snake.getSnakeTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().y());

    snake.increaseSize();
    snake.increaseSize();
    snake.move();
    snake.move();

    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().x());
    Assertions.assertEquals(5, snake.getSnakeHead().cell().getPoint().y());
    Assertions.assertEquals(5, snake.getSnakeTail().cell().getPoint().x());
    Assertions.assertEquals(1, snake.getSnakeTail().cell().getPoint().y());
  }

}