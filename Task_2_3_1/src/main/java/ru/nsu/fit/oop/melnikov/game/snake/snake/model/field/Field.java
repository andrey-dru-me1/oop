package ru.nsu.fit.oop.melnikov.game.snake.snake.model.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.snake.model.field.cell.FieldCell;

public class Field {

  private static final Random RANDOM = new Random();
  private final FieldCell[][] cells;
  private final int width;
  private final int height;
  private final List<EmptyFieldCell> emptyFieldCells;
  private Optional<EmptyFieldCell> appleField;

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

    this.generateApple();
  }

  public FieldCell getCell(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IndexOutOfBoundsException();
    }
    return cells[x][y];
  }

  public boolean isApple() {
    return appleField.isPresent();
  }

  public void generateApple() {
    appleField = Optional.of(emptyFieldCells.get(RANDOM.nextInt(emptyFieldCells.size())));
    appleField.get().putApple();
  }

}
