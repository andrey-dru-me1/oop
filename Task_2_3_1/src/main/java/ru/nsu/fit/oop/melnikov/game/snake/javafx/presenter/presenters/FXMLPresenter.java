package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.fxml.loaders.FXMLLoadersRepository;

public class FXMLPresenter {

  protected FXMLLoadersRepository loadersRepository;
  protected Stage stage;
  protected Scene prevScene;

  public void setFXMLLoadersRepository(FXMLLoadersRepository repository) {
    loadersRepository = repository;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public void setPrevScene(Scene prevScene) {
    this.prevScene = prevScene;
  }
}
