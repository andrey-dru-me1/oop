package ru.nsu.fit.oop.melnikov.game.snake.javafx;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.fxml.loaders.FXMLLoadersRepository;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.game.GameScreenPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.FXMLScreen;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.Resources;

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
    stage.setScene(loadersRepository.getRoot(FXMLScreen.MAIN_MENU));
    stage.show();
  }

  private void initLoadersRepository() throws IOException {
    loadersRepository =
        new FXMLLoadersRepository(
            stage,
            List.of(
                FXMLScreen.LISTEN_KEY,
                FXMLScreen.CHANGE_KEYS,
                FXMLScreen.GAME_SCREEN,
                FXMLScreen.SETTINGS,
                FXMLScreen.GAME_END,
                FXMLScreen.MAIN_MENU,
                FXMLScreen.SELECT_MAP));
  }

  @Override
  public void start(Stage stage) throws IOException {
    this.stage = stage;
    initLoadersRepository();
    initStage();
  }

  @Override
  public void stop() throws Exception {
    GameScreenPresenter gameScreenPresenter =
        loadersRepository.getPresenter(FXMLScreen.GAME_SCREEN);
    gameScreenPresenter.stopAll();

    ObjectMapper mapper = new ObjectMapper();
    mapper
        .writerWithDefaultPrettyPrinter()
        .writeValue(
            new File(Resources.GAME_SETTINGS_FILE),
            GameSettings.INSTANCE);
    super.stop();
  }
}
