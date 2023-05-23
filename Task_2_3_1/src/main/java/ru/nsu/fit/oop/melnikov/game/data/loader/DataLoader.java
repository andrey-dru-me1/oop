package ru.nsu.fit.oop.melnikov.game.data.loader;

import java.util.*;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

/**
* Loads map from .txt special file to snake model objects.
*/
public class DataLoader {

  private static final Map<String, Direction> DIRECTIONS =
      Map.of(
          "right", Direction.RIGHT,
          "up", Direction.UP,
          "down", Direction.DOWN,
          "left", Direction.LEFT);
  private final Field field;
  private final Snake snake;

  public DataLoader(String filename) {
    List<Point<Integer>> snakeIntPoints;
    Scanner scanner =
        new Scanner(Objects.requireNonNull(getClass().getResourceAsStream("/" + filename)));

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

    field = new Field(cells);

    scanner.skip("\n");

    scanner.reset();
    int size = scanner.nextInt();
    snakeIntPoints = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      snakeIntPoints.add(i, new Point<>(scanner.nextInt(), scanner.nextInt()));
    }

    snake = new Snake(field, snakeIntPoints);

    snake.setDirection(DIRECTIONS.get(scanner.next()));

  }

  public Field getField() {
    return field;
  }

  public Snake getSnake() {
    return snake;
  }
}
