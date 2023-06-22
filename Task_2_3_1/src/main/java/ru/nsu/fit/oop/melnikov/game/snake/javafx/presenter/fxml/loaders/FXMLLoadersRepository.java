package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.fxml.loaders;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.FXMLPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.FXMLScreen;

/**
 * Contains all the loaders so instead of loading new view from fxml each time you can just use this
 * class to get required screen by FXMLScreen enum.
 */
public class FXMLLoadersRepository {

  private final Map<FXMLScreen, FXMLLoader> loaders;

  public FXMLLoadersRepository(Stage primaryStage, Collection<FXMLScreen> screens)
      throws IOException {
    loaders = new HashMap<>(screens.size() * 2);
    for (FXMLScreen screen : screens) {
      FXMLLoader loader = new FXMLLoader(screen.getScreenUrl());
      loader.load();

      FXMLPresenter presenter = loader.getController();
      presenter.setFXMLLoadersRepository(this);
      presenter.setStage(primaryStage);

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
