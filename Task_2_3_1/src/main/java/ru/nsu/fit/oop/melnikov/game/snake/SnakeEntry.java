package ru.nsu.fit.oop.melnikov.game.snake;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.GameScreenPresenter;

public class SnakeEntry extends Application {

  private static final double TITLE_BAR_HEIGHT = 29;
  private static final double WINDOW_SIZE = 800;
  private Stage stage;
  private GameScreenPresenter presenter;

  public static void main(String[] args) {
    launch(args);
  }

  private void initStage() throws IOException {
    stage.setTitle("Snake the game");
    stage
        .getIcons()
        .add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
    stage.setWidth(WINDOW_SIZE);
    stage.setHeight(WINDOW_SIZE + TITLE_BAR_HEIGHT);
    stage.setScene(initScene());
    stage.show();
  }

  private Scene initScene() throws IOException {
    Scene scene;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/game_screen.fxml"));
    Parent root = loader.load();
    scene = new Scene(root);
    presenter = loader.getController();
    return scene;
  }

  @Override
  public void start(Stage stage) throws IOException {
    this.stage = stage;
    initStage();
    presenter.initialize("big_map.txt");
  }

  @Override
  public void stop() throws Exception {
    presenter.stopAll();
    super.stop();
  }
}
