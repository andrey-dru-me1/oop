package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class MainMenuPresenter extends FXMLPresenter {
  public void onNewGameClick() {
      FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.GAME_SCREEN);
    primaryStage.setScene(new Scene(loader.getRoot()));
    GameScreenPresenter presenter = loader.getController();
    presenter.initialize("big_map.txt");
  }
}
