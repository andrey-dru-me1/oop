package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
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
  private Runnable onSettingsClose;

  public void initialize(GameSettings gameSettings, Runnable onSettingsClose) {
    this.gameSettings = gameSettings;
    gameSpeed.setValue(
        (double) (gameSettings.getTickDelay() * (100 + MIN_DELAY)) / MAX_DELAY - MIN_DELAY);
    this.onSettingsClose = onSettingsClose;
  }

  public void onContinueClick() {
    gameSettings.setTickDelay(
        (int) ((gameSpeed.getValue() + MIN_DELAY) * MAX_DELAY / (100 + MIN_DELAY)));
    stage.setScene(prevScene);
    onSettingsClose.run();
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
}
