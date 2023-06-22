package ru.nsu.fit.oop.melnikov.game.snake.model.field;

import java.util.*;

import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Apple;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

/** Field is an unmodifiable set of cells where snake is going to move. */
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
    for (Cell cell : cells) {
      cellMatrix[cell.getX()][cell.getY()] = cell;
    }

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (cellMatrix[i][j] == null) {
          cellMatrix[i][j] = new Cell(i, j);
        }
      }
    }

    this.cells = cellMatrix;

    appleCells = new LinkedList<>();

    noPlaceForApple = false;
  }

  /**
   * Shows if there are some spaces for apples to be placed.
   *
   * @return {@code true} if there is such a cell where apple can be places and {@code false}
   *     otherwise
   */
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

  /**
   * Returns cell with specified coordinates. Throws IndexOutOfBoundException if either x or y out
   * of field size.
   *
   * @param x x cell coordinate
   * @param y y cell coordinate
   * @return cell if such exists
   */
  public Cell getCell(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IndexOutOfBoundsException();
    }
    return cells[x][y];
  }

  /**
   * Delegates duties to {@link Field#getCell(int, int)}
   *
   * @param intPoint point with specified x and y to search cell by them
   * @return cell if such exists
   */
  public Cell getCell(Point<Integer> intPoint) {
    return this.getCell(intPoint.getX(), intPoint.getY());
  }

  public int applesCount() {
    return appleCells.size();
  }

  /**
   * Clean all the existing apples and generate the specified count of new ones.
   *
   * @param appleCount count of apples to generate
   */
  public void regenerateApples(int appleCount) {
    Collection<Cell> snapshot = new ArrayList<>(appleCells);
    for (Cell appleCell : snapshot) {
      eatApple(appleCell);
    }
    for (int i = 0; i < appleCount; i++) {
      generateApple();
    }
  }

  /**
   * Generates new single apple in random place.
   *
   * <p>Does nothing if there is no place for apple.
   */
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

  /**
   * Correctly removes apple from the field.
   *
   * @param appleCell cell where one of apples is located
   */
  public void eatApple(Cell appleCell) {
    if (appleCell.contains(Apple.class)) {
      appleCells.remove(appleCell);
      appleCell.remove(Apple.class);
    }
  }

  public Point<Integer> calculateNextPoint(Point<Integer> point, Direction direction) {
    Point<Integer> nextPoint = direction.nextPoint(point);

    if (nextPoint.getX() >= width) {
      nextPoint = new Point<>(0, nextPoint.getY());
    } else if (nextPoint.getX() < 0) {
      nextPoint = new Point<>(width - 1, nextPoint.getY());
    } else if (nextPoint.getY() >= height) {
      nextPoint = new Point<>(nextPoint.getX(), 0);
    } else if (nextPoint.getY() < 0) {
      nextPoint = new Point<>(nextPoint.getX(), height - 1);
    }
    return nextPoint;
  }

}
