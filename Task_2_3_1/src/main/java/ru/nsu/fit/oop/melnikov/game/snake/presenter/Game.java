package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.CellDTO;

public class Game {

  private final Snake snake;
  private final Timeline timer;
  private final int delay;
  private final Runnable whenCrashed;
  private final Runnable whenWon;
  private final CellDTO[][] cellDTOS;
  private Direction direction;

  public Game(
      Snake snake, CellDTO[][] cellDTOS, int delay, Runnable whenCrashed, Runnable whenWon) {
    this.snake = snake;
    this.delay = delay;
    this.whenCrashed = whenCrashed;
    this.timer = new Timeline();
    this.timer.setCycleCount(Animation.INDEFINITE);
    this.whenWon = whenWon;
    this.cellDTOS = cellDTOS;
    this.direction = snake.getDirection();
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void start() {
    timer
        .getKeyFrames()
        .add(
            new KeyFrame(
                new Duration(delay), // This is how often it updates in milliseconds
                t -> {
                  if(isOpposite(direction, snake.getDirection())) {
                    direction = snake.getDirection();
                  }
                  snake.move(direction);
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

  private boolean isOpposite(Direction d1, Direction d2) {
    return d1 == Direction.DOWN && d2 == Direction.UP
            || d1 == Direction.UP && d2 == Direction.DOWN
            || d1 == Direction.RIGHT && d2 == Direction.LEFT
            || d1 == Direction.LEFT && d2 == Direction.RIGHT;
  }

  public void stop() {
    timer.stop();
  }
}
