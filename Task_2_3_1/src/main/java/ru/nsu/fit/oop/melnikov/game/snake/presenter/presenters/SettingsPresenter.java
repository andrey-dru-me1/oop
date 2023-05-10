package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.Game;

public class SettingsPresenter extends FXMLPresenter {
  @FXML public VBox settingsSet;
  @FXML public Slider gameSpeed;
  private Game game;
  private Canvas canvas;

  private static final int MIN_DELAY = 20;
  private static final int MAX_DELAY = 400;

  public void initialize(Game game, Canvas canvas, int delay) {
    game.pause();
    this.game = game;
    this.canvas = canvas;
    gameSpeed.setValue((double) delay * (100 + 20) / MAX_DELAY - MIN_DELAY);
  }

  public void onOKClick() {
    game.setDelay((int)((gameSpeed.getValue() + MIN_DELAY) * MAX_DELAY / (100 +  MIN_DELAY)));
    if (settingsSet.getParent() instanceof Pane pane) {
      pane.getChildren().remove(settingsSet);
    }
    canvas.getScene().setOnKeyPressed(keyEvent -> canvas.getOnKeyPressed().handle(keyEvent));
    game.play();
  }
}
