package ru.nsu.fit.oop.melnikov.game.data.saver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.SnakeNode;

public class DataSaver {

  private DataSaver() {
    throw new IllegalStateException("Utility class");
  }

  public static void save(Field field, Snake snake, String filename) throws IOException {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
      StringBuilder result = new StringBuilder();

      result.append(field.getWidth()).append(" ").append(field.getHeight()).append("\n");

      //field
      for (FieldCell[] row : field.getCells()) {
        for (FieldCell cell : row) {
          result.append((cell instanceof Wall) ? '#' : ' ');
        }
        result.append('\n');
      }

      result.append(snake.size()).append("\n");

      //snake
      for (SnakeNode snakeNode : snake.getNodes()) {
        Point point = snakeNode.cell().getPoint();
        result.append(point.x()).append(" ").append(point.y()).append("\n");
      }

      result.append('\n');

      bufferedWriter.write(String.valueOf(result));
    }
  }

}
