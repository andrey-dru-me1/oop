package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.GameState;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class EndScreenPresenter extends FXMLPresenter {
  @FXML public Pane parent;
  @FXML public Label score;
  private GameState gameState;

  public void onPlayAgainClick() {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.GAME_SCREEN);
    GameScreenPresenter presenter = loader.getController();
    presenter.initialize(gameState.getMapName());
    stage.setScene(loader.getRoot());
  }

  public void updateScore(GameState gameState) {
    this.gameState = gameState;
    score.setText("Score: " + gameState.getScore());
  }

  public void onExitClick() {
    stage.close();
  }

  public void onMainMenuClick() {
    stage.setScene(loadersRepository.getRoot(FXMLScreens.MAIN_MENU));
  }
}
