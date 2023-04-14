package ru.nsu.fit.oop.melnikov.game.snake.model.field;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.NoPlaceForAppleException;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.FieldCell;

public class Field {

  private static final Random RANDOM = new Random();
  private final FieldCell[][] cells;
  private final int width;
  private final int height;
  private final List<EmptyFieldCell> emptyFieldCells;
  private final List<EmptyFieldCell> appleFields;

  public Field(FieldCell[][] cells) {
    this.cells = cells;
    width = cells.length;
    height = cells[0].length;

    this.emptyFieldCells = new ArrayList<>();

    for (FieldCell[] fieldCells : cells) {
      for (FieldCell cell : fieldCells) {
        if (cell instanceof EmptyFieldCell fieldCell) {
          emptyFieldCells.add(fieldCell);
        }
      }
    }

    appleFields = new LinkedList<>();

  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public FieldCell[][] getCells() {
    return cells;
  }

  public FieldCell getCell(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IndexOutOfBoundsException();
    }
    return cells[x][y];
  }

  public FieldCell getCell(Point point) {
    return this.getCell(point.x(), point.y());
  }

  public int applesCount() {
    return appleFields.size();
  }

  public void generateApple() throws NoPlaceForAppleException {
    EmptyFieldCell newAppleField;
    int i = RANDOM.nextInt(emptyFieldCells.size());
    int startI = i;
    while ((newAppleField = emptyFieldCells.get(i)).getSnake().isPresent()
        || newAppleField.hasApple()) {
      i = (i + 1) % emptyFieldCells.size();
      if (i == startI) {
        throw new NoPlaceForAppleException();
      }
    }
    newAppleField.putApple();
    appleFields.add(newAppleField);
  }

  public void eatApple(EmptyFieldCell appleCell) {
    if (appleCell.hasApple()) {
      appleFields.remove(appleCell);
      appleCell.eatApple();
    }
  }

}
