package ru.nsu.fit.oop.melnikov.game.data.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.SnakeNode;

public class DataLoader {

  private final Field field;
  private final Snake snake;

  public DataLoader(String filename) throws SnakeInSnakeException {
    List<SnakeNode> nodes;
    Scanner scanner =
        new Scanner(
            Objects.requireNonNull(getClass().getResourceAsStream("/" + filename)));

    int width = scanner.nextInt();
    int height = scanner.nextInt();
    scanner.skip("\n");

    FieldCell[][] cells = new FieldCell[width][height];
    scanner.useDelimiter("");

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        switch (scanner.next()) {
          case "#" -> cells[j][i] = new Wall(j, i);
          case " " -> cells[j][i] = new EmptyFieldCell(j, i);
          default -> throw new IllegalStateException();
        }
      }
      scanner.skip("\n");
    }

    field = new Field(cells);

    int size = scanner.nextInt();
    nodes = new ArrayList<>(size);
    scanner.reset();
    for (int i = 0; i < size; i++) {
      if (field.getCell(scanner.nextInt(), scanner.nextInt())
          instanceof EmptyFieldCell emptyFieldCell) {
        nodes.add(i, new SnakeNode(emptyFieldCell));
      }
    }

    snake = new Snake(field, nodes);
  }

  public Field getField() {
    return field;
  }

  public Snake getSnake() {
    return snake;
  }
}
