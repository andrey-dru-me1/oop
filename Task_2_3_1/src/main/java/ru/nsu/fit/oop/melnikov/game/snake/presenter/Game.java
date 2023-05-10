package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.CellDTO;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.GameScreenPresenter;

public class Game {

  private final Snake snake;
  private final GameScreenPresenter presenter;
  private final Deque<Direction> directionQueue;
  private CellDTO[][] cellDTOS;
  private Timeline timeline;
  private int delay;
  private KeyFrame keyFrame;

  public Game(Snake snake, int delay, GameScreenPresenter presenter) {
    this.snake = snake;
    this.delay = delay;
    this.timeline = new Timeline();
    this.timeline.setCycleCount(Animation.INDEFINITE);
    this.directionQueue = new ArrayDeque<>(2);
    directionQueue.add(snake.getDirection());
    this.presenter = presenter;
    timeline.setDelay(new Duration(delay));
  }

  public void setCellDTOS(CellDTO[][] cellDTOS) {
    this.cellDTOS = cellDTOS;
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int millisDelay) {
    this.delay = millisDelay;
    keyFrame = new KeyFrame(new Duration(delay), this::onTimerTriggers);
    timeline.stop();
    timeline = new Timeline();
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.getKeyFrames().add(keyFrame);
  }

  public void addDirection(Direction direction) {
    if (!directionQueue.isEmpty()
        && (direction.isOpposite(directionQueue.peekLast())
            || direction.equals(directionQueue.peek()))) {
      return;
    }
    this.directionQueue.add(direction);
  }

  public void start() {
    keyFrame = new KeyFrame(new Duration(delay), this::onTimerTriggers);
    timeline.getKeyFrames().add(keyFrame);
    timeline.playFrom(new Duration(delay));
  }

  public Direction getDirection() {
    return snake.getDirection();
  }

  private void onTimerTriggers(ActionEvent actionEvent) {
    Direction direction = snake.getDirection();
    if (directionQueue.size() > 0) {
      direction = directionQueue.poll();
      if (direction.isOpposite(snake.getDirection())) {
        direction = snake.getDirection();
      }
    }
    snake.move(direction);
    presenter.setScore(snake.getScore());
    if (snake.isDestroyed()) {
      onSnakeDestroyed();
      return;
    }
    if (snake.getField().isNoPlaceForApple()) {
      timeline.stop();
      presenter.fillCanvas(cellDTOS, Color.GREEN);
      return;
    }
    redraw();
  }

  private void redraw() {
    for (CellDTO[] row : cellDTOS) {
      for (CellDTO cellDTO : row) {
        cellDTO.drawObjects();
      }
    }
  }

  private void onSnakeDestroyed() {
    timeline.stop();
    presenter.onGameEnd();
  }

  public void pause() {
    timeline.pause();
  }

  public void play() {
    timeline.playFrom(new Duration(delay));
  }

  public void stop() {
    timeline.stop();
  }
}
