package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.melnikov.game.data.loader.DataLoader;
import ru.nsu.fit.oop.melnikov.game.snake.SnakeEntry;
import ru.nsu.fit.oop.melnikov.game.snake.model.Game;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.NoPlaceForAppleException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class GamePresenter {

  private final SnakeEntry snakeEntry;
  private Game model;
  private Snake snake;

  public GamePresenter(SnakeEntry snakeEntry) {
    this.snakeEntry = snakeEntry;
  }

  public void initGameFromFile(String filename)
      throws SnakeInSnakeException, NoPlaceForAppleException {

    DataLoader dataLoader = new DataLoader(filename);
    Field field = dataLoader.getField();

    CellPresenter[][] cellPresenters = new CellPresenter[field.getWidth()][field.getHeight()];

    for (int i = 0; i < field.getWidth(); i++) {
      FieldCell[] row = field.getCells()[i];
      for (int j = 0; j < field.getHeight(); j++) {
        FieldCell cell = row[j];
        cellPresenters[i][j] = new CellPresenter(cell, snakeEntry.createRectangle(i, j));
        cell.addPropertyChangeListener(cellPresenters[i][j]);
      }
    }

    snake = dataLoader.getSnake();

    model =
        new Game(
            snake,
            400,
            () -> snakeEntry.changeScene("death"),
            () -> snakeEntry.changeScene("victory"));
    model.start();

    field.generateApple();
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
    model.stop();
  }

}
