package ru.nsu.fit.oop.melnikov.game.snake;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.fxml.loaders.FXMLLoadersRepository;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.game.GameScreenPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.FXMLScreens;

public class SnakeEntry extends Application {

  private static final double TITLE_BAR_HEIGHT = 29;
  private static final double WINDOW_SIZE = 800;
  private Stage stage;
  private FXMLLoadersRepository loadersRepository;

  public static void main(String[] args) {
    launch(args);
  }

  private void initStage() {
    stage.setTitle("Snake the game");
    stage
        .getIcons()
        .add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
    stage.setWidth(WINDOW_SIZE);
    stage.setHeight(WINDOW_SIZE + TITLE_BAR_HEIGHT);
    stage.setScene(loadersRepository.getRoot(FXMLScreens.MAIN_MENU));
    stage.show();
  }

  private void initLoadersRepository() throws IOException {
    loadersRepository =
        new FXMLLoadersRepository(
            stage,
            new GameSettings(),
            List.of(
                FXMLScreens.LISTEN_KEY,
                FXMLScreens.CHANGE_KEYS,
                FXMLScreens.GAME_SCREEN,
                FXMLScreens.SETTINGS,
                FXMLScreens.GAME_END,
                FXMLScreens.MAIN_MENU,
                FXMLScreens.SELECT_MAP));
  }

  @Override
  public void start(Stage stage) throws IOException {
    this.stage = stage;
    initLoadersRepository();
    initStage();
  }

  @Override
  public void stop() throws Exception {
    loadersRepository.<GameScreenPresenter>getPresenter(FXMLScreens.GAME_SCREEN).stopAll();
    super.stop();
  }
}
