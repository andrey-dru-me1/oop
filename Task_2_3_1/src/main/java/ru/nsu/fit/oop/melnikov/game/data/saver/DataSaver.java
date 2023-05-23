package ru.nsu.fit.oop.melnikov.game.data.saver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

/**
* Saves map from snake model objects to .txt file.
*/
public class DataSaver {
  private static final Map<Direction, String> DIRECTIONS =
          Map.of(
                  Direction.RIGHT, "right",
                  Direction.UP, "up",
                  Direction.DOWN, "down",
                  Direction.LEFT, "left");

  private DataSaver() {
    throw new IllegalStateException("Utility class");
  }

/**
*
 * @param field field where snake is moving on
 * @param snake snake which is under user's control
 * @param filename name of a file to save to
 * @throws IOException when there is some error with a file
*/
  public static void save(Field field, Snake snake, String filename) throws IOException {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
      StringBuilder result = new StringBuilder();

      // field
      for (Cell[] row : field.getCells()) {
        for (Cell cell : row) {
          result.append((cell.contains(Wall.class)) ? '#' : ' ');
        }
        result.append("|\n");
      }
      result.append(".\n");

      result.append(snake.size()).append("\n");

      // snake
      for (Cell cell : snake.getNodes()) {
        result.append(cell.getX()).append(" ").append(cell.getY()).append("\n");
      }

      result.append(DIRECTIONS.get(snake.getDirection())).append('\n');

      bufferedWriter.write(String.valueOf(result));
    }
  }
}
