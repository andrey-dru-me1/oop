package ru.nsu.fit.oop.melnikov.game.data.loader;

import java.util.*;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.ObservableSnake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class DataLoader {

  private final Field field;
  private final Snake snake;

  public DataLoader(String filename) {
    List<Point> snakePoints;
    Scanner scanner =
        new Scanner(Objects.requireNonNull(getClass().getResourceAsStream("/" + filename)));

    Queue<Cell> cells = new ArrayDeque<>();
    scanner.useDelimiter("");

    int x = 0;
    int y = 0;
    while(true) {
      String token = scanner.next();
      if(token.equals(".")) {
        break;
      }
      if (token.equals("#") || token.equals(" ")) {
        Cell cell = new Cell(x, y);
        if (token.equals("#")) {
          cell.add(new Wall());
        }
        cells.add(cell);
        x++;
      }
      else if(token.equals("\n")) {
        x = 0;
        y++;
      }
      else {
        throw new IllegalStateException();
      }
    }

    field = new Field(cells);

    scanner.skip("\n");

    int size = scanner.nextInt();
    snakePoints = new ArrayList<>(size);
    scanner.reset();
    for (int i = 0; i < size; i++) {
      snakePoints.add(i, new Point(scanner.nextInt(), scanner.nextInt()));
    }

    snake = new ObservableSnake(field, snakePoints);
  }

  public Field getField() {
    return field;
  }

  public Snake getSnake() {
    return snake;
  }
}
