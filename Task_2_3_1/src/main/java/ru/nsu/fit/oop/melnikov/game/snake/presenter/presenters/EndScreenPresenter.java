package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class EndScreenPresenter extends FXMLPresenter {
  @FXML public Pane parent;

  public void onPlayAgainClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.GAME_SCREEN);
    GameScreenPresenter presenter = loader.getController();
    presenter.initialize("big_map.txt");
    primaryStage.setScene(loader.<Parent>getRoot().getScene());
  }

  public void onExitClick() {
    if (parent.getScene().getWindow() instanceof Stage stage) {
      stage.close();
    }
  }
}
