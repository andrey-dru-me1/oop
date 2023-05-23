package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.game.GameScreenPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.FXMLScreen;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.TextureName;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;

public class SettingsPresenter extends FXMLPresenter {
  @FXML public VBox settingsSet;
  @FXML public Slider gameSpeed;
  @FXML public ComboBox<Label> texturePackChanger;
  @FXML public Slider appleCount;

  @FXML
  private void initialize() {
    for (String textureDirectory : TextureName.getTextureDirectories()) {
      Label label = new Label(textureDirectory);
      label.setTextFill(Color.BLACK);
      texturePackChanger.getItems().add(label);
      if (textureDirectory.equals(GameSettings.INSTANCE.getTextureName())) {
        texturePackChanger.setValue(label);
      }
    }
  }

  public void init() {
    gameSpeed.setValue(40.0 / GameSettings.INSTANCE.getTickDelay());
    appleCount.setValue(GameSettings.INSTANCE.getApplesPercentage());
  }

  public void onContinueClick() {
    saveSettings();
    stage.setScene(prevScene);
    if (loadersRepository.getRoot(FXMLScreen.GAME_SCREEN) == prevScene) {
      GameScreenPresenter gameScreenPresenter =
          loadersRepository.getPresenter(FXMLScreen.GAME_SCREEN);

      Field field = gameScreenPresenter.getField();

      gameScreenPresenter.getGame().redraw();
      gameScreenPresenter
          .getGame()
          .regenerateApples(
              Math.max(
                  GameSettings.INSTANCE.getApplesPercentage()
                      * (field.getWidth() * field.getHeight()) / 100,
                  1));
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
    GameSettings.INSTANCE.setTickDelay((int) (40 / gameSpeed.getValue()));
    GameSettings.INSTANCE.setTextureName(texturePackChanger.getValue().getText());
    GameSettings.INSTANCE.setApplesPercentage((int) appleCount.getValue());
  }
}
