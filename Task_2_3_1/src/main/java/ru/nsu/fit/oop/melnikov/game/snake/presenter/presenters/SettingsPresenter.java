package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class SettingsPresenter extends FXMLPresenter {
  private static final int MIN_DELAY = 20;
  private static final int MAX_DELAY = 400;
  @FXML public VBox settingsSet;
  @FXML public Slider gameSpeed;
  private GameSettings gameSettings;
  private Scene prevScene;
  private Runnable onSettingsClose;

  public void initialize(GameSettings gameSettings, Runnable onSettingsClose) {
    this.gameSettings = gameSettings;
    gameSpeed.setValue(
        (double) (gameSettings.getTickDelay() * (100 + MIN_DELAY)) / MAX_DELAY - MIN_DELAY);
    this.onSettingsClose = onSettingsClose;
  }

  public void setPrevScene(Scene prevScene) {
    this.prevScene = prevScene;
  }

  public void onOKClick() {
    gameSettings.setTickDelay(
        (int) ((gameSpeed.getValue() + MIN_DELAY) * MAX_DELAY / (100 + MIN_DELAY)));
    stage.setScene(prevScene);
    onSettingsClose.run();
  }

  public void onChangeKeysClick() {
    stage.setScene(loadersRepository.getRootNode(FXMLScreens.CHANGE_KEYS).getScene());
  }
}
