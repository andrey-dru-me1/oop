package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.settings;

import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.settings.SnakeKey;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreen;

public class ChangeKeysPresenter extends FXMLPresenter {

  private static final Map<String, SnakeKey> KEY_MATCHING =
      Map.of(
          "UP", SnakeKey.UP,
          "DOWN", SnakeKey.DOWN,
          "LEFT", SnakeKey.LEFT,
          "RIGHT", SnakeKey.RIGHT,
          "MENU", SnakeKey.MENU);
  @FXML public VBox keys;

  public void onBackClick() {
    stage.setScene(prevScene);
  }

  public void updateKeySet() {
    for (Node child : keys.getChildren()) {
      if (child instanceof BorderPane pane
          && (pane.getCenter() instanceof Label keyCodes
              && pane.getLeft() instanceof Label snakeKeyName)) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<KeyCode, SnakeKey> keyMapping = gameSettings.getKeys();
        for (KeyCode keyCode : keyMapping.keySet()) {
          if (keyMapping.get(keyCode) != KEY_MATCHING.get(snakeKeyName.getText())) {
            continue;
          }
          if (!stringBuilder.isEmpty()) {
            stringBuilder.append(", ");
          }
          stringBuilder.append(keyCode);
        }
        keyCodes.setText(stringBuilder.toString());
      }
    }
  }

  private void createModalKeyListener(SnakeKey snakeKey) {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.LISTEN_KEY);
    Stage keyListenerStage = loader.getRoot();
    ListenKeyPresenter listenKeyPresenter = loader.getController();
    listenKeyPresenter.setKeyToChange(snakeKey);
    keyListenerStage.showAndWait();
    updateKeySet();
  }

  public void onLeftKeyChange() {
    createModalKeyListener(SnakeKey.LEFT);
  }

  public void onRightKeyChange() {
    createModalKeyListener(SnakeKey.RIGHT);
  }

  public void onUpKeyChange() {
    createModalKeyListener(SnakeKey.UP);
  }

  public void onDownKeyChange() {
    createModalKeyListener(SnakeKey.DOWN);
  }

  public void onMenuKeyChange() {
    createModalKeyListener(SnakeKey.MENU);
  }
}
