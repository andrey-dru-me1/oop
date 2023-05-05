package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsPresenter {
  @FXML public VBox settingsSet;
  @FXML public Slider gameSpeed;
  private Game game;

  private static final int MIN_DELAY = 20;
  private static final int MAX_DELAY = 400;

  public void initialize(Game game, int delay) {
    game.pause();
    this.game = game;
    gameSpeed.setValue((double) delay * (100 + 20) / MAX_DELAY - MIN_DELAY);
  }

  public void onOKClick() {
    game.setDelay((int)((gameSpeed.getValue() + MIN_DELAY) * MAX_DELAY / (100 +  MIN_DELAY)));
    if (settingsSet.getParent() instanceof Pane pane) {
      pane.getChildren().remove(settingsSet);
    }
    game.play();
  }
}
