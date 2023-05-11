package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

import javafx.scene.Scene;

public class ChangeKeysPresenter extends FXMLPresenter {

  private Scene prevScene;

  public void onBackClick() {
    stage.setScene(prevScene);
  }

  public void setPrevScene(Scene prevScene) {
    this.prevScene = prevScene;
  }
}
