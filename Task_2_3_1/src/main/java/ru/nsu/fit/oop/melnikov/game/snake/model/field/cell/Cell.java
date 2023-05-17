package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.EmptyCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public class Cell extends Point<Integer> {

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

  public boolean add(CellObject newCellObject) {
    List<CellObject> queueSnapshot = cellObjects.stream().toList();
    boolean result = cellObjects.add(newCellObject);
    for (CellObject cellObject : queueSnapshot) {
      cellObject.onAnotherCellObjectAppearance(newCellObject);
    }
    return result;
  }

  public boolean remove(Class<? extends CellObject> desiredClass) {
    for (CellObject cellObject : cellObjects) {
      if (desiredClass.isInstance(cellObject)) {
        cellObjects.remove(cellObject);
        return true;
      }
    }
    return false;
  }

  public boolean contains(Class<? extends CellObject> desiredClass) {
    for (CellObject cellObject : cellObjects) {
      if (desiredClass.isInstance(cellObject)) {
        return true;
      }
    }
    return false;
  }

  public <T> T getExemplar(Class<T> desiredClass) {
    for (CellObject cellObject : cellObjects) {
      if (desiredClass.isInstance(cellObject)) {
        return desiredClass.cast(cellObject);
      }
    }
    throw new NoSuchElementException();
  }
}
