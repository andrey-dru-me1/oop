package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.VBox;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreen;

public class SettingsPresenter extends FXMLPresenter {
  private static final int MIN_DELAY = 20;
  private static final int MAX_DELAY = 400;
  @FXML public VBox settingsSet;
  @FXML public Slider gameSpeed;
  private GameSettings gameSettings;

  public void initialize(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
    gameSpeed.setValue(
        (double) (gameSettings.getTickDelay() * (100 + MIN_DELAY)) / MAX_DELAY - MIN_DELAY);
  }

  public void onContinueClick() {
    stage.setScene(prevScene);
  }

  public void onChangeKeysClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.CHANGE_KEYS);
    ChangeKeysPresenter changeKeysPresenter = loader.getController();
    changeKeysPresenter.updateKeySet();
    changeKeysPresenter.setPrevScene(stage.getScene());
    stage.setScene(loader.getRoot());
  }

  public void onMainMenuClick() {
    stage.setScene(loadersRepository.getRoot(FXMLScreen.MAIN_MENU));
  }

  public void onExitClick() {
    stage.close();
  }

  public void onGameSpeedDragDone() {
    gameSettings.setTickDelay(
            (int) ((gameSpeed.getValue() + MIN_DELAY) * MAX_DELAY / (100 + MIN_DELAY)));
  }
}
