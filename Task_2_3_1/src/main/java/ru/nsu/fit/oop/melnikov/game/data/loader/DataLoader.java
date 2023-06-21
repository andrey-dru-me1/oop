package ru.nsu.fit.oop.melnikov.game.data.loader;

import java.util.*;
import ru.nsu.fit.oop.melnikov.game.snake.model.GameData;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.BotSnake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.SnakePoint;

/** Loads map from .txt special file to snake model objects. */
public class DataLoader {

  private static final Map<String, Direction> DIRECTIONS =
      Map.of(
          "right", Direction.RIGHT,
          "up", Direction.UP,
          "down", Direction.DOWN,
          "left", Direction.LEFT);

  private DataLoader() {}

  public static GameData load(String filename) {
    List<SnakePoint> snakeIntPoints;
    Scanner scanner =
        new Scanner(Objects.requireNonNull(DataLoader.class.getResourceAsStream("/" + filename)));

    Queue<Cell> cells = new ArrayDeque<>();
    scanner.useDelimiter("");

    int x = 0;
    int y = 0;
    while (true) {
      String token = scanner.next();
      if (token.equals(".")) {
        break;
      }
      if (token.equals("#") || token.equals(" ")) {
        Cell cell = new Cell(x, y);
        if (token.equals("#")) {
          cell.add(new Wall());
        }
        cells.add(cell);
        x++;
      } else if (token.equals("|")) {
        x = 0;
        y++;
        scanner.skip("\n");
      } else {
        throw new IllegalStateException();
      }
    }

    Field field = new Field(cells);

    scanner.skip("\n");

    scanner.reset();
    int snakeCount = scanner.nextInt();
    List<Snake> snakes = new ArrayList<>(snakeCount);
    for(int i = 0; i < snakeCount; i++) {
      String snakeType = scanner.next();
      int size = scanner.nextInt();
      snakeIntPoints = new ArrayList<>(size);
      for (int j = 0; j < size; j++) {
        snakeIntPoints.add(j, new SnakePoint(scanner.nextInt(), scanner.nextInt()));
      }

      Snake snake;
      if(snakeType.equals("player")) snake = new Snake(field, snakeIntPoints);
      else snake = new BotSnake(field, snakeIntPoints);
      snake.setDirection(DIRECTIONS.get(scanner.next()));

      snakes.add(snake);
    }

    return new GameData(field, snakes);
  }
}
