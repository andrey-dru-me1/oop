package ru.nsu.fit.oop.melnikov.game.data.loader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.game.snake.model.GameData;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

class DataLoaderTest {

  @Test
  void test() {
    GameData gameData = DataLoader.load("test.txt");
    Snake snake = gameData.snakes().get(0);

    Assertions.assertEquals(new Point<>(1, 1), snake.getTailCell());
    Assertions.assertEquals(new Point<>(3, 1), snake.getHeadCell());

    Field field = gameData.field();

    Assertions.assertEquals(7, field.getWidth());
    Assertions.assertEquals(7, field.getHeight());

    Assertions.assertTrue(field.getCell(0, 0).contains(Wall.class));
    Assertions.assertTrue(field.getCell(6, 3).contains(Wall.class));
    Assertions.assertFalse(field.getCell(5, 5).contains(Wall.class));
  }
}
