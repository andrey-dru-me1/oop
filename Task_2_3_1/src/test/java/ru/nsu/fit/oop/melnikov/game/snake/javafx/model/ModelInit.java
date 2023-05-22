package ru.nsu.fit.oop.melnikov.game.snake.javafx.model;

import java.util.LinkedList;
import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class ModelInit {

  protected static final int SIZE = 7;
  protected final Snake snake;
  protected final Field field;

  public ModelInit() {

    Cell[][] fieldCells = new Cell[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        fieldCells[i][j] = new Cell(i, j);
      }
    }

    for (int i = 1; i < SIZE - 1; i++) {
      fieldCells[i][0].add(new Wall());
      fieldCells[0][i].add(new Wall());
      fieldCells[i][SIZE - 1].add(new Wall());
      fieldCells[SIZE - 1][i].add(new Wall());
    }
    fieldCells[0][0].add(new Wall());
    fieldCells[0][SIZE - 1].add(new Wall());
    fieldCells[SIZE - 1][0].add(new Wall());
    fieldCells[SIZE - 1][SIZE - 1].add(new Wall());

    field = new Field(fieldCells);

    List<Point<Integer>> nodes = new LinkedList<>();
    for (int i = 1; i <= 3; i++) {
      if (!field.getCell(i, 1).contains(Wall.class)) {
        nodes.add(new Point<>(i, 1));
      }
    }

    snake = new Snake(field, nodes);
  }
}
