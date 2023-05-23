package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.game.GameScreenPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.FXMLScreen;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.TextureName;

public class SettingsPresenter extends FXMLPresenter {
  @FXML public VBox settingsSet;
  @FXML public Slider gameSpeed;
  @FXML public ComboBox<Label> texturePackChanger;

  @FXML
  private void initialize() {
    for (String textureDirectory : TextureName.getTextureDirectories()) {
      Label label = new Label(textureDirectory);
      texturePackChanger.getItems().add(label);
      if(textureDirectory.equals(GameSettings.INSTANCE.getTextureName())) {
        texturePackChanger.setValue(label);
      }
    }
  }

  public void init() {
    gameSpeed.setValue(GameSettings.INSTANCE.getTickDelay());
  }

  public void onContinueClick() {
    saveSettings();
    stage.setScene(prevScene);
    if(loadersRepository.getRoot(FXMLScreen.GAME_SCREEN) == prevScene) {
      GameScreenPresenter gameScreenPresenter = loadersRepository.getPresenter(FXMLScreen.GAME_SCREEN);
      gameScreenPresenter.getGame().redraw();
    }
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
    GameSettings.INSTANCE.setTextureName(texturePackChanger.getValue().getText());
  }
}
