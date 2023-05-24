package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.menu;

import javafx.fxml.FXMLLoader;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.settings.SettingsPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.FXMLScreen;

public class MainMenuPresenter extends FXMLPresenter {
  public void onNewGameClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.SELECT_MAP);
    stage.setScene(loader.getRoot());
  }

  public void onExitClick() {
    stage.close();
  }

  /** Initializes and runs settings screen. */
  public void onSettingsClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreen.SETTINGS);
    SettingsPresenter settingsPresenter = loader.getController();
    settingsPresenter.init();
    settingsPresenter.setPrevScene(stage.getScene());
    stage.setScene(loader.getRoot());
  }
}
