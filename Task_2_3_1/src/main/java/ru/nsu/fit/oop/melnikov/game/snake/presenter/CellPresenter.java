package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.*;

public class CellPresenter implements PropertyChangeListener {
  private final Canvas canvas;
  private final Rect<Double> rect;
  private final Cell cell;

  public CellPresenter(Cell cell, Canvas canvas, Rect<Double> rect) {
    this.canvas = canvas;
    this.rect = rect;
    this.cell = cell;
    fillRect();
  }

  private void fillRect() {
    Color color;
    if (cell.contains(Wall.class)) {
      color = Color.BLACK;
    } else if (cell.contains(SnakeNode.class)) {
      color = Color.GREEN;
    } else if (cell.contains(Apple.class)) {
      color = Color.RED;
    } else {
      color = Color.WHITE;
    }
    canvas.getGraphicsContext2D().setFill(color);
    canvas
        .getGraphicsContext2D()
        .fillRect(rect.p1().getX(), rect.p1().getY(), rect.p2().getX() - rect.p1().getX(), rect.p2().getY() - rect.p1().getY());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    fillRect();
  }
}
