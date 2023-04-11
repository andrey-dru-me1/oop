package ru.nsu.fit.oop.melnikov.game.snake.data.loader;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.exceptions.crash.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.snake.Snake;

class DataLoaderTest {

  @Test
  void test() throws IOException, SnakeInSnakeException {
    DataLoader dataLoader = new DataLoader("test.txt");
    Snake snake = dataLoader.getSnake();

    Assertions.assertEquals(new Point(1, 1), snake.getTail().cell().getPoint());
    Assertions.assertEquals(new Point(3, 1), snake.getHead().cell().getPoint());

    Field field = dataLoader.getField();

    Assertions.assertEquals(7, field.getWidth());
    Assertions.assertEquals(7, field.getHeight());

    Assertions.assertTrue(field.getCell(0, 0) instanceof Wall);
    Assertions.assertTrue(field.getCell(6, 3) instanceof Wall);
    Assertions.assertTrue(field.getCell(5, 5) instanceof EmptyFieldCell);

  }

}