package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class MainMenuPresenter extends FXMLPresenter {
  public void onNewGameClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.SELECT_MAP);
    primaryStage.setScene(loader.<Node>getRoot().getScene());
  }
}
