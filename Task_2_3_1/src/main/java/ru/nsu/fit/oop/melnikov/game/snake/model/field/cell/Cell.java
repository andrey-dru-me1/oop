package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.EmptyCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

/** Cell is an element of field. It has its own coordinates and objects which is linked to cell. */
public class Cell extends Point<Integer> {

  /** Queue of cell objects ordered by increasing of its priority. */
  private final PriorityQueue<CellObject> cellObjects;

  public Cell(int x, int y) {
    super(x, y);

    // Straight ordering
    Comparator<CellObject> comparator = Comparator.comparingInt(CellObject::getPriority);
    cellObjects = new PriorityQueue<>(1, comparator);
    cellObjects.add(new EmptyCell());
  }

  public PriorityQueue<CellObject> getCellObjects() {
    return cellObjects;
  }

  /**
   * Adds new object, links it to the cell and polls other objects if they want to do something on
   * object appearance.
   *
   * @param newCellObject object to add to current cell
   * @return {@code true} (as specified by {@link Collection#add})
   */
  public boolean add(CellObject newCellObject) {
    List<CellObject> queueSnapshot = cellObjects.stream().toList();
    boolean result = cellObjects.add(newCellObject);
    for (CellObject cellObject : queueSnapshot) {
      cellObject.onAnotherCellObjectAppearance(newCellObject);
    }
    return result;
  }

  /**
   * Removes cell object of specified class.
   *
   * @param desiredClass class, object of which to remove
   * @return {@code true} if there is something to remove, false otherwise
   */
  public boolean remove(Class<? extends CellObject> desiredClass) {
    for (CellObject cellObject : cellObjects) {
      if (desiredClass.isInstance(cellObject)) {
        cellObjects.remove(cellObject);
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if object of desired class is licked to current cell.
   *
   * @param desiredClass class to search for
   * @return true if there is object of specified class and false otherwise
   */
  public boolean contains(Class<? extends CellObject> desiredClass) {
    for (CellObject cellObject : cellObjects) {
      if (desiredClass.isInstance(cellObject)) {
        return true;
      }
    }
    return false;
  }
}
