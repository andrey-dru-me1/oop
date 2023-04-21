package ru.nsu.fit.oop.melnikov.game.snake;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.GamePresenter;

public class SnakeEntry extends Application {

  private static final double TITLE_BAR_HEIGHT = 31;
  private static final double WINDOW_SIZE = 800;
  Scene scene;
  private GamePresenter presenter;
  private Stage stage;
  private GridPane grid;

  public static void main(String[] args) {
    Application.launch(args);
  }

  private void initStage() {
    stage.setTitle("Snake the game");
    stage
        .getIcons()
        .add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
    stage.setWidth(WINDOW_SIZE);
    stage.setHeight(WINDOW_SIZE + TITLE_BAR_HEIGHT);
    stage.setScene(initScene());
    stage.show();
  }

  private Scene initScene() {
    scene = new Scene(grid);
    scene.setOnKeyPressed(presenter::onKeyPressed);
    return scene;
  }

  private void initPresenter() {
    presenter = new GamePresenter(this);
  }

  public void changeScene(String sceneName) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      InputStream stream = getClass().getResourceAsStream("/fxmls/" + sceneName + ".fxml");
      Parent root = fxmlLoader.load(stream);
      Platform.runLater(() -> stage.setScene(new Scene(root)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Rectangle createRectangle(int fieldSize, int x, int y) {
    Rectangle newRect = new Rectangle();
    var size =
        Bindings.min(scene.widthProperty(), scene.heightProperty()).divide(fieldSize).subtract(2);
    newRect.widthProperty().bind(size);
    newRect.heightProperty().bind(size);
    grid.add(newRect, x, y);
    return newRect;
  }

  private void initGrid() {
    grid = new GridPane();
    grid.setHgap(2);
    grid.setVgap(2);
  }

  @Override
  public void start(Stage stage) {
    initPresenter();
    initGrid();
    this.stage = stage;
    initStage();
    presenter.initGameFromFile("default.txt");
  }

  @Override
  public void stop() throws Exception {
    presenter.stopAll();
    super.stop();
  }
}
