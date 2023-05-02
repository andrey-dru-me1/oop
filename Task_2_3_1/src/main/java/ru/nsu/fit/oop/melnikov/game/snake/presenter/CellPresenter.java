package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.util.Duration;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.*;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects.*;

public class CellPresenter implements PropertyChangeListener {
  private final Canvas canvas;
  private final Rect<Double> rect;
  private final Cell cell;

  private AnimationTimer timer;
  private Timeline timeline;

  public CellPresenter(Cell cell, Canvas canvas, Rect<Double> rect) {
    this.canvas = canvas;
    this.rect = rect;
    this.cell = cell;
    fillRect();
  }

  private void fillRect() {
    CellObjectFactory cellObjectFactory = new CellObjectFactory("default");
    for (CellObject cellObject : cell.getCellObjects()) {
      Image image = cellObjectFactory.create(cellObject).getImage();

      DoubleProperty alpha = new SimpleDoubleProperty(0.0);

      stopThreads();
      timeline =
          new Timeline(
              new KeyFrame(Duration.millis(0), new KeyValue(alpha, 0.0)),
              new KeyFrame(Duration.millis(5000), new KeyValue(alpha, 1.0)));

      timer =
          new AnimationTimer() {
            @Override
            public void handle(long now) {
              drawCanvas(alpha.get());
            }

            private void drawCanvas(double alpha) {
              canvas.getGraphicsContext2D().setGlobalAlpha(alpha);
              canvas
                  .getGraphicsContext2D()
                  .drawImage(image, rect.x(), rect.y(), rect.width(), rect.height());
            }

            @Override
            public void stop() {
              drawCanvas(1.0);
              super.stop();
            }
          };

      timer.start();
      timeline.play();
    }
  }

  public void stopThreads() {
    if (timer != null) {
      timer.stop();
    }
    if (timeline != null) {
      timeline.stop();
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    fillRect();
  }
}
