package ru.nsu.fit.oop.melnikov.game.data.loader;

import java.util.*;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.IntPoint;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

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
    List<IntPoint> snakeIntPoints;
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

    int size = scanner.nextInt();
    snakeIntPoints = new ArrayList<>(size);
    scanner.reset();
    for (int i = 0; i < size; i++) {
      snakeIntPoints.add(i, new IntPoint(scanner.nextInt(), scanner.nextInt()));
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
