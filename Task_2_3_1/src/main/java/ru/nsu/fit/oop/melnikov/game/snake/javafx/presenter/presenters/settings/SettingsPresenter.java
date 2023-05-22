package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.FXMLScreen;

public class SettingsPresenter extends FXMLPresenter {
  @FXML public VBox settingsSet;
  @FXML public Slider gameSpeed;

  public void init() {
    gameSpeed.setValue(GameSettings.INSTANCE.getTickDelay());
  }

  public void onContinueClick() {
    saveSettings();
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
    saveSettings();
    stage.setScene(loadersRepository.getRoot(FXMLScreen.MAIN_MENU));
  }

  public void onExitClick() {
    saveSettings();
    stage.close();
  }

  public void saveSettings() {
    GameSettings.INSTANCE.setTickDelay((int) gameSpeed.getValue());
  }

}
