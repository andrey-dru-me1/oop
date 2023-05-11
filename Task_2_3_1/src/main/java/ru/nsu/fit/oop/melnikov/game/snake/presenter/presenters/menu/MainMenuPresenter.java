package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.menu;

import javafx.fxml.FXMLLoader;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.settings.SettingsPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class MainMenuPresenter extends FXMLPresenter {
  public void onNewGameClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.SELECT_MAP);
    stage.setScene(loader.getRoot());
  }

  public void onExitClick() {
    stage.close();
  }

  public void onSettingsClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.SETTINGS);
    SettingsPresenter settingsPresenter = loader.getController();
    settingsPresenter.initialize(gameSettings, () -> {});
    settingsPresenter.setPrevScene(stage.getScene());
    stage.setScene(loader.getRoot());
  }
}
