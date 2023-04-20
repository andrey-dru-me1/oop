package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public class FieldCell {

  protected final PropertyChangeSupport support;
  private final Point point;

  protected FieldCell(int x, int y) {
    this(new Point(x, y));
  }

  protected FieldCell(Point point) {
    this.point = point;
    support = new PropertyChangeSupport(this);
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
