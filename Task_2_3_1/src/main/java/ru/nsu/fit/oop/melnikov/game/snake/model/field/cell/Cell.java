package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.EmptyCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public class Cell {

  protected final PropertyChangeSupport support;
  private final Point point;

  private final PriorityQueue<CellObject> cellObjects;

  public Cell(int x, int y) {
    this(new Point(x, y));
  }

  public Cell(Point point) {
    this.point = point;
    support = new PropertyChangeSupport(this);

    // Descending ordering
    Comparator<CellObject> comparator =
        (o1, o2) -> Integer.compare(o2.getPriority(), o1.getPriority());
    cellObjects = new PriorityQueue<>(1, comparator);
    cellObjects.add(new EmptyCell());
  }

  public PriorityQueue<CellObject> getCellObjects() {
    return cellObjects;
  }

  public boolean add(CellObject newCellObject) {
    List<CellObject> queueSnapshot = cellObjects.stream().toList();
    boolean result = cellObjects.add(newCellObject);
    queueSnapshot.parallelStream()
        .forEach(cellObject -> cellObject.onAnotherCellObjectAppearance(newCellObject));
    support.fireIndexedPropertyChange("add", 0, queueSnapshot.get(0), cellObjects.peek());
    return result;
  }

  public boolean remove(Class<? extends CellObject> desiredClass) {
    for (CellObject cellObject : cellObjects) {
      if (desiredClass.isInstance(cellObject)) {
        cellObjects.remove(cellObject);
        support.fireIndexedPropertyChange("remove", 0, cellObject, cellObjects.peek());
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

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  public Point getPoint() {
    return point;
  }
}
