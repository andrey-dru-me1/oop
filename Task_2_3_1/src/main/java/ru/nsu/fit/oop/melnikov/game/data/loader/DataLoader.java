package ru.nsu.fit.oop.melnikov.game.data.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
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

    int width = scanner.nextInt();
    int height = scanner.nextInt();
    scanner.skip("\n");

    Cell[][] cells = new Cell[width][height];
    scanner.useDelimiter("");

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String token = scanner.next();
        if (token.equals("#") || token.equals(" ")) {
          cells[i][j] = new Cell(i, j);
          if (token.equals("#")) {
            cells[i][j].add(new Wall());
          }
        } else {
          throw new IllegalStateException();
        }
      }
      scanner.skip("\n");
    }

    field = new Field(cells);

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
