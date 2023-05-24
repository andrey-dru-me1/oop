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

  /** Dynamically forms a list of texture names. */
  @FXML
  private void initialize() {
    texturePackChanger.getItems().clear();
    for (String textureDirectory : TextureName.getTextureDirectories()) {
      Label label = new Label(textureDirectory);
      label.setTextFill(Color.BLACK);
      texturePackChanger.getItems().add(label);
      if (textureDirectory.equals(GameSettings.INSTANCE.getTextureName())) {
        texturePackChanger.setValue(label);
      }
    }
  }

  /** Sets all the settings according to already existing ones. */
  public void init() {
    gameSpeed.setValue(40.0 / GameSettings.INSTANCE.getTickDelay());
    appleCount.setValue(GameSettings.INSTANCE.getApplesPercentage());
  }

  /**
   * Saves settings and loads previous scene. If previous scene is a game scene then redraws game
   * screen and regenerates apples in game.
   */
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
                      * (field.getWidth() * field.getHeight())
                      / 100,
                  1));
    }
  }

  /** Loads screen that changes keys for snake actions. */
  public void onChangeKeysClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.CHANGE_KEYS);
    ChangeKeysPresenter changeKeysPresenter = loader.getController();
    changeKeysPresenter.updateKeySet();
    changeKeysPresenter.setPrevScene(stage.getScene());
    stage.setScene(loader.getRoot());
  }

  /** Loads main menu scene. */
  public void onMainMenuClick() {
    saveSettings();
    stage.setScene(loadersRepository.getRoot(FXMLScreen.MAIN_MENU));
  }

  /** Closes the window and ends all the game. */
  public void onExitClick() {
    saveSettings();
    stage.close();
  }

  /** Saves all the settings to gameSettings. */
  public void saveSettings() {
    GameSettings.INSTANCE.setTickDelay((int) (40 / gameSpeed.getValue()));
    GameSettings.INSTANCE.setTextureName(texturePackChanger.getValue().getText());
    GameSettings.INSTANCE.setApplesPercentage((int) appleCount.getValue());
  }
}
