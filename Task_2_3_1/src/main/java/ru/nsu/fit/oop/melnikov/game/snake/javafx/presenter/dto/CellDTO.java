package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto;

import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.beans.binding.NumberBinding;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.Game;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.Rect;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.CellObjectDTOSRepository;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;

/** Represents model's cell for javafx. */
public class CellDTO {
  private final GraphicsContext context;
  private final Rect<NumberBinding> rect;
  private final CellObjectDTOSRepository cellObjectDTOSRepository;
  private final Collection<Pair<AnimationTimer, Timeline>> animations;
  private final Cell cell;
  private final Game game;

  public CellDTO(
      Game game,
      Cell cell,
      Rect<NumberBinding> rect,
      CellObjectDTOSRepository repository,
      GraphicsContext context) {
    this.game = game;
    this.context = context;
    this.rect = rect;
    this.cellObjectDTOSRepository = repository;
    this.cell = cell;
    this.animations = new ArrayList<>();
    rect.width().addListener((observable, oldValue, newValue) -> drawObjects());
    drawObjects();
  }

  /** Draw all the objects located on cell. */
  public void drawObjects() {
    for (CellObject cellObject : cell.getCellObjects()) {
      stopAnimations();
      cellObjectDTOSRepository.get(cell, cellObject).draw(context, game, rect);
    }
  }

  /** Seal for the future. Project hasn't any animation for now. */
  public void stopAnimations() {
    Collection<Pair<AnimationTimer, Timeline>> snapshot = new ArrayList<>(animations);
    for (Pair<AnimationTimer, Timeline> pair : snapshot) {
      pair.getKey().stop();
      pair.getValue().stop();
      animations.remove(pair);
    }
  }
}
