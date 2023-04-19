package ru.nsu.fit.oop.melnikov.game.snake.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.nsu.fit.oop.melnikov.game.data.loader.DataLoader;
import ru.nsu.fit.oop.melnikov.game.snake.model.Game;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.NoPlaceForAppleException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.ObservableSnake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.SnakeNode;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws SnakeInSnakeException, IOException {

    primaryStage.setTitle("Snake the game");
    primaryStage
        .getIcons()
        .add(
            new Image(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResourceAsStream("icon.png"))));
    primaryStage.setWidth(800);
    primaryStage.setHeight(831);

    DataLoader loader = new DataLoader("test.txt");
    Field field = loader.getField();
    GridPane grid = new GridPane();
    double rectSize = primaryStage.getWidth() / field.getWidth() - 2;

    FieldRect[][] fieldRects = new FieldRect[field.getWidth()][field.getHeight()];

    try {

      for (int i = 0; i < field.getWidth(); i++) {
        FieldCell[] row = field.getCells()[i];
        for (int j = 0; j < field.getHeight(); j++) {
          FieldCell cell = row[j];
          fieldRects[i][j] = new FieldRect(rectSize, rectSize);
          cell.addPropertyChangeListener(fieldRects[i][j]);
          fieldRects[i][j].setFill(Paint.valueOf((cell instanceof Wall) ? "BLACK" : "WHITE"));
          grid.add(fieldRects[i][j], cell.getPoint().x(), cell.getPoint().y());
        }
      }
      grid.setHgap(2);
      grid.setVgap(2);

      ObservableSnake snake = loader.getSnake();
      for (SnakeNode snakeNode : snake.getNodes()) {
        Point point = snakeNode.cell().getPoint();
        fieldRects[point.x()][point.y()].setFill(Paint.valueOf("GREEN"));
      }

      Scene scene = new Scene(grid);

      Game game =
          new Game(
              snake,
              500,
              () -> {
                try {
                  FXMLLoader fxmlLoader = new FXMLLoader();
                  URL xmlUrl = Main.class.getClassLoader().getResource("fxmls/death.fxml");
                  fxmlLoader.setLocation(xmlUrl);
                  Parent root = fxmlLoader.load();
                  Platform.runLater(() -> primaryStage.setScene(new Scene(root)));
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
                },
                () -> {
                  try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    URL xmlUrl = Main.class.getClassLoader().getResource("fxmls/victory.fxml");
                    fxmlLoader.setLocation(xmlUrl);
                    Parent root = fxmlLoader.load();
                    Platform.runLater(() -> primaryStage.setScene(new Scene(root)));
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
              });
      game.start();

      scene.setOnKeyPressed(
          keyEvent -> {
            switch (keyEvent.getCode()) {
              case LEFT -> snake.setDirection(Direction.LEFT);
              case RIGHT -> snake.setDirection(Direction.RIGHT);
              case DOWN -> snake.setDirection(Direction.DOWN);
              case UP -> snake.setDirection(Direction.UP);
              default -> {
                // No need to do anything on another keyboard keys
              }
            }
          });

      field.generateApple();

      primaryStage.setScene(scene);

      primaryStage.show();
    } catch (NoPlaceForAppleException e) {
    }
  }

  private static class FieldRect extends Rectangle implements PropertyChangeListener {

    public FieldRect(double width, double height) {
      super(width, height);
    }

    public FieldRect(double width, double height, Paint fill) {
      super(width, height, fill);
    }

    public FieldRect(double x, double y, double width, double height) {
      super(x, y, width, height);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      switch (evt.getPropertyName()) {
        case "snake" -> {
          if (evt.getNewValue() instanceof Optional<?>) {
            this.setFill(Paint.valueOf("WHITE"));
          } else if (evt.getNewValue() instanceof Snake) {
            this.setFill(Paint.valueOf("GREEN"));
          }
        }
        case "apple" -> {
          if (evt.getNewValue() instanceof Boolean res) {
            this.setFill(Paint.valueOf(Boolean.TRUE.equals(res) ? "RED" : "GREEN"));
          }
        }
        default -> {
          // No need to do anything on another keyboard keys
        }
      }
    }
  }

  @Override
  public void stop() throws Exception {
    System.exit(0);
    super.stop();
  }
}
