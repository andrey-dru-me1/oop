package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.settings;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.settings.SnakeKey;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class ChangeKeysPresenter extends FXMLPresenter {

  public void onBackClick() {
    stage.setScene(prevScene);
  }

  private void createModalKeyListener(SnakeKey snakeKey) {
    FXMLLoader loader = loadersRepository.getLoader(FXMLScreens.LISTEN_KEY);
    Stage keyListenerStage = loader.getRoot();
    ListenKeyPresenter listenKeyPresenter = loader.getController();
    listenKeyPresenter.setKeyToChange(snakeKey);
    keyListenerStage.showAndWait();
  }

  public void onLeftKeyChange() {
    createModalKeyListener(SnakeKey.LEFT);
  }

  public void onRightKeyChange() {
    createModalKeyListener(SnakeKey.RIGHT);
  }

  public void onUpKeyChange() {
    createModalKeyListener(SnakeKey.UP);
  }

  public void onDownKeyChange() {
    createModalKeyListener(SnakeKey.DOWN);
  }

  public void onMenuKeyChange() {
    createModalKeyListener(SnakeKey.MENU);
  }
}
