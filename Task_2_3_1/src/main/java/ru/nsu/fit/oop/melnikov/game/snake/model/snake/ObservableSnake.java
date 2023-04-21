package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public class ObservableSnake extends Snake {

  private final PropertyChangeSupport support;

  public ObservableSnake(Field field, List<Point> snakePoints) {
    super(field, snakePoints);
    support = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

  @Override
  public void destroy() {
    support.firePropertyChange("destroy", this, null);
    Arrays.stream(support.getPropertyChangeListeners())
        .forEach(support::removePropertyChangeListener);
    super.destroy();
  }
}
