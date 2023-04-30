package ru.nsu.fit.oop.melnikov.game.snake.model.field;

import java.util.*;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Apple;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.IntPoint;

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

  public Field(Collection<Cell> cells) {

    this.emptyCells = new ArrayList<>();

    int w = 0;
    int h = 0;
    for (Cell cell : cells) {
      if (cell.getX() > w) {
        w = cell.getX();
      }
      if (cell.getY() > h) {
        h = cell.getY();
      }
      if (!cell.contains(Wall.class)) {
        emptyCells.add(cell);
      }
    }
    this.width = w + 1;
    this.height = h + 1;

    Cell[][] cellMatrix = new Cell[width][height];
    for(Cell cell : cells) {
      cellMatrix[cell.getX()][cell.getY()] = cell;
    }

    for(int i = 0; i < width; i++) {
      for(int j = 0; j < height; j++) {
        if(cellMatrix[i][j] == null) {
          cellMatrix[i][j] = new Cell(i, j);
        }
      }
    }

    this.cells = cellMatrix;

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

  public Cell getCell(IntPoint intPoint) {
    return this.getCell(intPoint.getX(), intPoint.getY());
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
    newAppleCell.add(
        new Apple(
            () -> {
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
