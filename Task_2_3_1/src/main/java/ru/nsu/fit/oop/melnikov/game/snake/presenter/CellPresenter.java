package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.*;

public class CellPresenter implements PropertyChangeListener {
  private final Rectangle rect;

  public CellPresenter(Cell cell, Rectangle rect) {
    this.rect = rect;
    if (cell.contains(Wall.class)) {
      rect.setFill(Color.BLACK);
    } else {
      if (cell.contains(SnakeNode.class)) {
        rect.setFill(Color.GREEN);
      } else if (cell.contains(Apple.class)) {
        rect.setFill(Color.RED);
      } else {
        rect.setFill(Color.WHITE);
      }
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getNewValue() instanceof Wall) {
      rect.setFill(Color.BLACK);
    } else if (evt.getNewValue() instanceof SnakeNode) {
      rect.setFill(Color.GREEN);
    } else if (evt.getNewValue() instanceof EmptyCell) {
      rect.setFill(Color.WHITE);
    } else if (evt.getNewValue() instanceof Apple) {
      rect.setFill(Color.RED);
    }
  }
}
