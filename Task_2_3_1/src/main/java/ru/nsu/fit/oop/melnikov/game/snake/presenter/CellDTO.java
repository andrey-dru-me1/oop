package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.util.Pair;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.*;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects.*;

public class CellDTO implements PropertyChangeListener {
  private final Canvas canvas;
  private final Rect<Double> rect;
  private final CellObjectDTOSFactory cellObjectDTOSFactory;
  private final Collection<Pair<AnimationTimer, Timeline>> animations;
  private final Cell cell;

  public CellDTO(Cell cell, Canvas canvas, Rect<Double> rect) {
    this.canvas = canvas;
    this.rect = rect;
    this.cellObjectDTOSFactory = new CellObjectDTOSFactory("default");
    this.cell = cell;
    this.animations = new ArrayList<>();
    drawObjects();
  }

  private void drawObjects() {
    for (CellObject cellObject : cell.getCellObjects()) {
      stopThreads();
      cellObjectDTOSFactory.create(cellObject).draw(canvas.getGraphicsContext2D(), rect).ifPresent(animations::add);
    }
  }

  public void stopThreads() {
    Collection<Pair<AnimationTimer, Timeline>> snapshot = new ArrayList<>(animations);
    for (Pair<AnimationTimer, Timeline> pair : snapshot) {
    pair.getKey().stop();
    pair.getValue().stop();
    animations.remove(pair);
  }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    drawObjects();
  }
}
