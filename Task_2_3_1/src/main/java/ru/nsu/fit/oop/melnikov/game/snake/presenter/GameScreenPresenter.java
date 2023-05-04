package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.melnikov.game.data.loader.DataLoader;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.CellDTO;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTOSRepository;

public class GameScreenPresenter {
  @FXML public Label scoreLabel;
  private Game game;

  private Field field;
  @FXML private Canvas canvas;
  private SimpleIntegerProperty score;

  public void setScore(int score) {
    this.score.set(score);
  }

  public Field getField() {
    return field;
  }

  @FXML
  public void initialize(String filename) {
    Snake snake;

    Scene scene = canvas.getScene();

    scene.setOnKeyPressed(this::onKeyPressed);

    DataLoader dataLoader = new DataLoader(filename);
    field = dataLoader.getField();

    CellDTO[][] cellDTOS = new CellDTO[field.getWidth()][field.getHeight()];

    NumberBinding rectSize = calculateRectSize();

    CellObjectDTOSRepository repository = new CellObjectDTOSRepository("default");

    for (int i = 0; i < field.getWidth(); i++) {
      Cell[] row = field.getCells()[i];
      for (int j = 0; j < field.getHeight(); j++) {
        Cell cell = row[j];
        cellDTOS[i][j] =
            new CellDTO(
                cell,
                canvas.getGraphicsContext2D(),
                new Rect<>(rectSize.multiply(i), rectSize.multiply(j), rectSize, rectSize),
                repository);
      }
    }

    snake = dataLoader.getSnake();

    game = new Game(snake, cellDTOS, 100, this);
    game.start();

    this.score = new SimpleIntegerProperty(0);
    scoreLabel.textProperty().bind((new SimpleStringProperty("Score: ")).concat(score.asString()));

    field.generateApple();
  }

  public void fillCanvas(CellDTO[][] cellDTOS, Color color) {
    for (CellDTO[] row : cellDTOS) {
      for (CellDTO cellDTO : row) {
        cellDTO.stopAnimations();
      }
    }
    canvas.getGraphicsContext2D().setFill(color);
    canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
  }

  private NumberBinding calculateRectSize() {
    NumberBinding result =
        Bindings.min(
            canvas
                .getScene()
                .heightProperty()
                .subtract(scoreLabel.heightProperty())
                .divide(field.getHeight()),
            canvas.getScene().widthProperty().divide(field.getWidth()));
    result.addListener(
        (observable, oldValue, newValue) -> {
          canvas.widthProperty().unbind();
          canvas.heightProperty().unbind();
          if ((canvas.getScene().getHeight() - scoreLabel.getHeight()) / field.getHeight()
              <= canvas.getScene().getWidth() / field.getWidth()) {
            DoubleBinding binding =
                canvas.getScene().heightProperty().subtract(scoreLabel.heightProperty());
            canvas.widthProperty().bind(binding.divide(field.getHeight()).multiply(field.getWidth()));
            canvas.heightProperty().bind(binding);
          } else {
            ReadOnlyDoubleProperty property = canvas.getScene().widthProperty();
            canvas.widthProperty().bind(property);
            canvas.heightProperty().bind(property.divide(field.getWidth()).multiply(field.getHeight()));
          }
        });
    return result;
  }

  private void onKeyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getCode()) {
      case LEFT -> game.setDirection(Direction.LEFT);
      case RIGHT -> game.setDirection(Direction.RIGHT);
      case DOWN -> game.setDirection(Direction.DOWN);
      case UP -> game.setDirection(Direction.UP);
      default -> {
        // No need to do anything on another keyboard keys
      }
    }
  }

  public void stopAll() {
    game.stop();
  }
}
