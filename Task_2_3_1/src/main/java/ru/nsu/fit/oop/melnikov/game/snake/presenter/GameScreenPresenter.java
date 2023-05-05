package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.melnikov.game.data.loader.DataLoader;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.CellDTO;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTOSRepository;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.JavafxDesigner;

public class GameScreenPresenter {
  @FXML public Label scoreLabel;
  @FXML public Pane pane;
  @FXML public BorderPane borderPane;
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

  public void initialize(String filename) {
    Snake snake;

    JavafxDesigner.makeMatchParent(borderPane);

    canvas.setOnKeyPressed(this::onKeyPressed);
    canvas.getScene().setOnKeyPressed(keyEvent -> canvas.getOnKeyPressed().handle(keyEvent));

    DataLoader dataLoader = new DataLoader(filename);
    field = dataLoader.getField();

    CellDTO[][] cellDTOS = new CellDTO[field.getWidth()][field.getHeight()];

    NumberBinding rectSize = calculateRectSize();

    canvas.heightProperty().bind(rectSize.multiply(field.getHeight()));
    canvas.widthProperty().bind(rectSize.multiply(field.getWidth()));

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
    return Bindings.min(
        canvas
            .getScene()
            .heightProperty()
            .subtract(scoreLabel.heightProperty())
            .divide(field.getHeight()),
        canvas.getScene().widthProperty().divide(field.getWidth()));
  }

  private void onKeyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getCode()) {
      case LEFT -> game.addDirection(Direction.LEFT);
      case RIGHT -> game.addDirection(Direction.RIGHT);
      case DOWN -> game.addDirection(Direction.DOWN);
      case UP -> game.addDirection(Direction.UP);
      case ESCAPE -> {
        try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/settings.fxml"));
          Parent parent = loader.load();
          JavafxDesigner.makeMatchParent(parent);
          SettingsPresenter settingsPresenter = loader.getController();
          settingsPresenter.initialize(game, game.getDelay());
          pane.getChildren().add(parent);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      default -> {
        // No need to do anything on another keyboard keys
      }
    }
  }

  public void stopAll() {
    game.stop();
  }
}
