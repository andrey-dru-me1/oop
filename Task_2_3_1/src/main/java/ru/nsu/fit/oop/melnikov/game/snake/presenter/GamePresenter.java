package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import static java.lang.Math.max;
import static java.lang.Math.min;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.melnikov.game.data.loader.DataLoader;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class GamePresenter {
  private Game game;
  private Snake snake;
  private Field field;
  @FXML private Canvas canvas;

  public Field getField() {
    return field;
  }

  public void initialize(String filename) {

    canvas.getScene().setOnKeyPressed(this::onKeyPressed);

    DataLoader dataLoader = new DataLoader(filename);
    field = dataLoader.getField();

    CellDTO[][] cellDTOS = new CellDTO[field.getWidth()][field.getHeight()];

    double rectSize = calculateRectSize();

    for (int i = 0; i < field.getWidth(); i++) {
      Cell[] row = field.getCells()[i];
      for (int j = 0; j < field.getHeight(); j++) {
        Cell cell = row[j];
        cellDTOS[i][j] =
            new CellDTO(
                cell, canvas, new Rect<>(rectSize * i, rectSize * j, rectSize, rectSize));
        cell.addPropertyChangeListener(cellDTOS[i][j]);
      }
    }

    snake = dataLoader.getSnake();

    game =
        new Game(
            snake,
            300,
            () -> {
              for (CellDTO[] row : cellDTOS) {
                for (CellDTO cellDTO : row) {
                  cellDTO.stopThreads();
                }
              }
              canvas.getGraphicsContext2D().setFill(Color.RED);
              canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            },
            () -> {
              for (CellDTO[] row : cellDTOS) {
                for (CellDTO cellDTO : row) {
                  cellDTO.stopThreads();
                }
              }
              canvas.getGraphicsContext2D().setFill(Color.GREEN);
              canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            });
    game.start();

    field.generateApple();
  }

  private double calculateRectSize() {
    double canvasSize = min(canvas.getHeight(), canvas.getWidth());
    int fieldSize = max(field.getHeight(), field.getWidth());
    return canvasSize / fieldSize;
  }

  public void onKeyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getCode()) {
      case LEFT -> snake.setDirection(Direction.LEFT);
      case RIGHT -> snake.setDirection(Direction.RIGHT);
      case DOWN -> snake.setDirection(Direction.DOWN);
      case UP -> snake.setDirection(Direction.UP);
      default -> {
        // No need to do anything on another keyboard keys
      }
    }
  }

  public void stopAll() {
    game.stop();
  }
}
