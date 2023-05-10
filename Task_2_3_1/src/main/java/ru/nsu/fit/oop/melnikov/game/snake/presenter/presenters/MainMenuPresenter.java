package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class MainMenuPresenter extends FXMLPresenter {
  public void onNewGameClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.SELECT_MAP);
    stage.setScene(loader.<Node>getRoot().getScene());
  }

  public void onExitClick() {
    stage.close();
  }

  public void onSettingsClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.SETTINGS);
    SettingsPresenter settingsPresenter = loader.getController();
    settingsPresenter.initialize(gameSettings, () -> {});
    settingsPresenter.setPrevScene(stage.getScene());
    stage.setScene(loader.<Node>getRoot().getScene());
  }
}
