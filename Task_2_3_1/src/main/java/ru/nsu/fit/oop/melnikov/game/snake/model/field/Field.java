package ru.nsu.fit.oop.melnikov.game.snake.model.field;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Apple;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public class Field {

  private static final Random RANDOM = new Random();
  private final Cell[][] cells;
  private final int width;
  private final int height;
  private final List<Cell> emptyCells;
  private final List<Cell> appleCells;
  private boolean noPlaceForApple;

  public Field(Cell[][] cells) {
    this.cells = cells;
    width = cells.length;
    height = cells[0].length;

    this.emptyCells = new ArrayList<>();

    for (Cell[] fieldCells : cells) {
      for (Cell cell : fieldCells) {
        if (!cell.contains(Wall.class)) {
          emptyCells.add(cell);
        }
      }
    }

    appleCells = new LinkedList<>();

    noPlaceForApple = false;
  }

  public boolean isNoPlaceForApple() {
    return noPlaceForApple;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Cell[][] getCells() {
    return cells;
  }

  public Cell getCell(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IndexOutOfBoundsException();
    }
    return cells[x][y];
  }

  public Cell getCell(Point point) {
    return this.getCell(point.x(), point.y());
  }

  public int applesCount() {
    return appleCells.size();
  }

  public void generateApple() {
    if (noPlaceForApple) {
      return;
    }
    Cell newAppleCell;
    int i = RANDOM.nextInt(emptyCells.size());
    int startI = i;
    while ((newAppleCell = emptyCells.get(i)).contains(SnakeNode.class)
        || newAppleCell.contains(Apple.class)) {
      i = (i + 1) % emptyCells.size();
      if (i == startI) {
        noPlaceForApple = true;
        return;
      }
    }
    Cell appleCell = newAppleCell;
    newAppleCell.add(new Apple(() -> {
      eatApple(appleCell);
      generateApple();
    }));
    appleCells.add(newAppleCell);
  }

  public void eatApple(Cell appleCell) {
    if (appleCell.contains(Apple.class)) {
      appleCells.remove(appleCell);
      appleCell.remove(Apple.class);
    }
  }
}
