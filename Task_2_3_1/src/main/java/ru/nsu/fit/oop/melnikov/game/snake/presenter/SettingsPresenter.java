package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsPresenter {
  @FXML public VBox settingsSet;

  public void onOKClick() {
    if (settingsSet.getParent() instanceof Pane pane) {
      pane.getChildren().remove(settingsSet);
    }
  }
}
