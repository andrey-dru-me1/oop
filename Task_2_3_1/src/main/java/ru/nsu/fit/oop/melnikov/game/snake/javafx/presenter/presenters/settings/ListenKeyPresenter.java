package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.settings;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings.SnakeKey;

public class ListenKeyPresenter extends FXMLPresenter {
  @FXML public Stage listenKeyStage;
  @FXML public Scene listenKeyScene;
  private SnakeKey keyToChange;

  @FXML
  private void initialize() {
    listenKeyScene.setOnKeyPressed(keyEvent -> {
      GameSettings.INSTANCE.putKey(keyEvent.getCode(), keyToChange);
      listenKeyStage.close();
    });
  }

  public void setKeyToChange(SnakeKey keyToChange) {
    this.keyToChange = keyToChange;
  }

  public void onCancelClick() {
    listenKeyStage.close();
  }
}
