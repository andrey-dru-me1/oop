package ru.nsu.fit.oop.melnikov.game.snake.model;

import java.util.LinkedList;
import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.SnakeNode;

public class ModelInit {

  protected static final int SIZE = 7;
  protected final Snake snake;
  protected final Field field;

  public ModelInit() throws SnakeInSnakeException {

    FieldCell[][] fieldCells = new FieldCell[SIZE][SIZE];

    for (int i = 1; i < SIZE - 1; i++) {
      fieldCells[0][i] = new Wall(0, i);
      fieldCells[i][0] = new Wall(i, 0);

      fieldCells[SIZE - 1][i] = new Wall(SIZE - 1, i);
      fieldCells[i][SIZE - 1] = new Wall(i, SIZE - 1);

      for (int j = 1; j < SIZE - 1; j++) {
        fieldCells[i][j] = new EmptyFieldCell(i, j);
      }
    }
    fieldCells[0][0] = new Wall(0, 0);
    fieldCells[0][SIZE - 1] = new Wall(0, SIZE - 1);
    fieldCells[SIZE - 1][0] = new Wall(SIZE - 1, 0);
    fieldCells[SIZE - 1][SIZE - 1] = new Wall(SIZE - 1, SIZE - 1);

    field = new Field(fieldCells);

    List<SnakeNode> nodes = new LinkedList<>();
    for (int i = 1; i <= 3; i++) {
      if (field.getCell(i, 1) instanceof EmptyFieldCell emptyFieldCell) {
        nodes.add(new SnakeNode(emptyFieldCell));
      }
    }

    snake = new Snake(field, nodes);

  }

}