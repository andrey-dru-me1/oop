package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.fxml.loaders.FXMLLoadersRepository;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.settings.GameSettings;

public class FXMLPresenter {

  protected FXMLLoadersRepository loadersRepository;
  protected Stage stage;
  protected GameSettings gameSettings;

  public void setGameSettings(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
  }

  public void setFXMLLoadersRepository(FXMLLoadersRepository repository) {
    loadersRepository = repository;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }
}
