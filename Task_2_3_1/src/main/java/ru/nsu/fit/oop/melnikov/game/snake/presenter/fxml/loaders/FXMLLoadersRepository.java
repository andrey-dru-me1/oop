package ru.nsu.fit.oop.melnikov.game.snake.presenter.fxml.loaders;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreen;

public class FXMLLoadersRepository {

  private final Map<FXMLScreen, FXMLLoader> loaders;

  public FXMLLoadersRepository(
      Stage primaryStage, GameSettings gameSettings, Collection<FXMLScreen> screens)
      throws IOException {
    loaders = new HashMap<>(screens.size() * 2);
    for (FXMLScreen screen : screens) {
      FXMLLoader loader = new FXMLLoader(screen.getScreenUrl());
      loader.load();
      if (loader.getController() instanceof FXMLPresenter presenter) {
        presenter.setFXMLLoadersRepository(this);
        presenter.setStage(primaryStage);
        presenter.setGameSettings(gameSettings);
      }
      loaders.put(screen, loader);
    }
  }

  public FXMLLoader getLoader(FXMLScreen screen) {
    return loaders.get(screen);
  }

  public <T extends FXMLPresenter> T getPresenter(FXMLScreen screen) {
    return getLoader(screen).getController();
  }

  public <T> T getRoot(FXMLScreen screen) {
    return getLoader(screen).getRoot();
  }
}