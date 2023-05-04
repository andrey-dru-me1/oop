package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EndScreenPresenter {
  @FXML public Pane parent;

  public void onPlayAgainClick() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/game_screen.fxml"));
    Scene scene = new Scene(loader.load());
    GameScreenPresenter presenter = loader.getController();
    presenter.initialize("big_map.txt");
    if (parent.getScene().getWindow() instanceof Stage stage) {
      stage.setScene(scene);
    }
  }

  public void onExitClick() {
    if (parent.getScene().getWindow() instanceof Stage stage) {
      stage.close();
    }
  }
}
