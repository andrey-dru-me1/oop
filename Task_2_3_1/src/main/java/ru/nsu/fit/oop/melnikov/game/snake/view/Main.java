package ru.nsu.fit.oop.melnikov.game.snake.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
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
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.SnakeNode;

public class Main extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  private static class SnakeListener implements PropertyChangeListener {

    private final Rectangle[][] rects;

    public SnakeListener(Rectangle[][] rects) {
      this.rects = rects;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      if(evt.getOldValue() == null) {
        if (evt.getNewValue() instanceof SnakeNode newValue) {
          Point point = newValue.cell().getPoint();
          rects[point.x()][point.y()].setFill(Paint.valueOf("GREEN"));
        }
      }
      else if (evt.getOldValue() instanceof SnakeNode oldValue) {
        Point point = oldValue.cell().getPoint();
        rects[point.x()][point.y()].setFill(Paint.valueOf("WHITE"));
      }
    }

  }

  @Override
  public void start(Stage primaryStage) throws IOException, SnakeInSnakeException, NoPlaceForAppleException {

//    FXMLLoader loader = new FXMLLoader();
//    URL xmlUrl = Main.class.getClassLoader().getResource("fxmls/field.fxml");
//    loader.setLocation(xmlUrl);
//    Parent root = loader.load();

    primaryStage.setTitle("Snake the game");
    primaryStage
        .getIcons()
        .add(
            new Image(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResourceAsStream("icon.png"))));
    primaryStage.setWidth(800);
    primaryStage.setHeight(831);

//    Scene scene = new Scene(root);
//    primaryStage.setScene(scene);

    DataLoader loader = new DataLoader("test.txt");
    Field field = loader.getField();
    GridPane grid = new GridPane();
    double rectSize = primaryStage.getWidth() / field.getWidth() - 2;

    Rectangle[][] rects = new Rectangle[field.getWidth()][field.getHeight()];

    for (int i = 0; i < field.getWidth(); i++ ) {
      FieldCell[] row = field.getCells()[i];
      for(int j = 0; j < field.getHeight(); j++) {
        FieldCell cell = row[j];
        rects[i][j] = new Rectangle(rectSize, rectSize);
        rects[i][j].setFill(Paint.valueOf((cell instanceof Wall) ? "BLACK" : "WHITE"));
        grid.add(rects[i][j], cell.getPoint().x(), cell.getPoint().y());
      }
    }
    grid.setHgap(2);
    grid.setVgap(2);

    ObservableSnake snake = loader.getSnake();
    for (SnakeNode snakeNode : snake.getNodes()) {
      Point point = snakeNode.cell().getPoint();
      rects[point.x()][point.y()].setFill(Paint.valueOf("GREEN"));
    }

    SnakeListener snakeListener = new SnakeListener(rects);
    snake.addPropertyChangeListener(snakeListener);

    Game game = new Game(snake, 500);
    game.start();

    Scene scene = new Scene(grid);

    scene.setOnKeyPressed(keyEvent -> {
      switch(keyEvent.getCode()) {
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
  }
}
