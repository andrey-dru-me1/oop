package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.CellDTO;

public class Game {

  private final Snake snake;
  private final Timeline timer;
  private final int delay;
  private final Runnable whenCrashed;
  private final Runnable whenWon;
  private final CellDTO[][] cellDTOS;

  public Game(Snake snake, CellDTO[][] cellDTOS, int delay, Runnable whenCrashed, Runnable whenWon) {
    this.snake = snake;
    this.delay = delay;
    this.whenCrashed = whenCrashed;
    this.timer = new Timeline();
    this.timer.setCycleCount(Animation.INDEFINITE);
    this.whenWon = whenWon;
    this.cellDTOS = cellDTOS;
  }

  public void start() {
    timer
        .getKeyFrames()
        .add(
            new KeyFrame(
                new Duration(delay), // This is how often it updates in milliseconds
                t -> {
                  snake.move();
                  if (snake.isDestroyed()) {
                    timer.stop();
                    whenCrashed.run();
                    return;
                  }
                  if (snake.getField().isNoPlaceForApple()) {
                    timer.stop();
                    whenWon.run();
                    return;
                  }
                  for (CellDTO[] row : cellDTOS) {
                    for (CellDTO cellDTO : row) {
                      cellDTO.drawObjects();
                    }
                  }
                }));
    timer.playFrom(new Duration(delay));
  }

  public void stop() {
    timer.stop();
  }
}
