package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.GameState;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class EndScreenPresenter extends FXMLPresenter {
  @FXML public Pane parent;
  @FXML public Label score;

  public void onPlayAgainClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.GAME_SCREEN);
    GameScreenPresenter presenter = loader.getController();
    presenter.initialize("big_map.txt");
    stage.setScene(loader.<Parent>getRoot().getScene());
  }

  public void updateScore(GameState gameState) {
    score.setText("Score: " + gameState.getScore());
  }

  public void onExitClick() {
    stage.close();
  }

  public void onMainMenuClick() {
    stage.setScene(loadersRepository.getRootNode(FXMLScreens.MAIN_MENU).getScene());
  }
}
