package ru.nsu.fit.oop.melnikov.game.snake.presenter.fxml.loaders;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.settings.GameSettings;

public class FXMLLoadersRepository {

  private final Map<String, FXMLLoader> loaders;

  public FXMLLoadersRepository(
      Stage primaryStage, GameSettings gameSettings, Collection<String> screenNames)
      throws IOException {
    loaders = new HashMap<>(screenNames.size() * 2);
    for (String screenName : screenNames) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/" + screenName + ".fxml"));
      loader.load();
      if (loader.getController() instanceof FXMLPresenter presenter) {
        presenter.setFXMLLoadersRepository(this);
        presenter.setStage(primaryStage);
        presenter.setGameSettings(gameSettings);
      }
      if (loader.getRoot() instanceof Parent parent) {
        new Scene(parent);
      }
      loaders.put(screenName, loader);
    }
  }

  public FXMLLoader getLoader(String screenName) {
    return loaders.get(screenName);
  }

  public <T extends FXMLPresenter> T getPresenter(String screenName) {
    return getLoader(screenName).getController();
  }

  public <T extends Node> T getRootNode(String screenName) {
    return getLoader(screenName).getRoot();
  }
}
