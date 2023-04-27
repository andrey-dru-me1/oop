package ru.nsu.fit.oop.melnikov.game.data.saver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class DataSaver {

  private DataSaver() {
    throw new IllegalStateException("Utility class");
  }

  public static void save(Field field, Snake snake, String filename) throws IOException {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
      StringBuilder result = new StringBuilder();

      // field
      for (Cell[] row : field.getCells()) {
        for (Cell cell : row) {
          result.append((cell.contains(Wall.class)) ? '#' : ' ');
        }
        result.append('\n');
      }
      result.append(".\n");

      result.append(snake.size()).append("\n");

      // snake
      for (Cell cell : snake.getNodes()) {
        result.append(cell.getX()).append(" ").append(cell.getY()).append("\n");
      }

      result.append('\n');

      bufferedWriter.write(String.valueOf(result));
    }
  }
}
