package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto;

import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.beans.binding.NumberBinding;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.*;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.Rect;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTOSRepository;

public class CellDTO {
  private final GraphicsContext context;
  private final Rect<NumberBinding> rect;
  private final CellObjectDTOSRepository cellObjectDTOSRepository;
  private final Collection<Pair<AnimationTimer, Timeline>> animations;
  private final Cell cell;

  public CellDTO(
      Cell cell,
      GraphicsContext context,
      Rect<NumberBinding> rect,
      CellObjectDTOSRepository repository) {
    this.context = context;
    this.rect = rect;
    this.cellObjectDTOSRepository = repository;
    this.cell = cell;
    this.animations = new ArrayList<>();
    rect.width().addListener((observable, oldValue, newValue) -> drawObjects());
    drawObjects();
  }

  public void drawObjects() {
    for (CellObject cellObject : cell.getCellObjects()) {
      stopAnimations();
      cellObjectDTOSRepository.get(cell, cellObject).draw(context, rect);
    }
  }

  public void stopAnimations() {
    Collection<Pair<AnimationTimer, Timeline>> snapshot = new ArrayList<>(animations);
    for (Pair<AnimationTimer, Timeline> pair : snapshot) {
      pair.getKey().stop();
      pair.getValue().stop();
      animations.remove(pair);
    }
  }
}
