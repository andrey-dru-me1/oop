package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.CellDTO;

public class Game {

  private final Snake snake;
  private final GameScreenPresenter presenter;
  private final CellDTO[][] cellDTOS;
  private final Deque<Direction> directionQueue;
  private Timeline timeline;
  private int delay;
  private KeyFrame keyFrame;

  public Game(Snake snake, CellDTO[][] cellDTOS, int delay, GameScreenPresenter presenter) {
    this.snake = snake;
    this.delay = delay;
    this.timeline = new Timeline();
    this.timeline.setCycleCount(Animation.INDEFINITE);
    this.cellDTOS = cellDTOS;
    this.directionQueue = new ArrayDeque<>(2);
    directionQueue.add(snake.getDirection());
    this.presenter = presenter;
    timeline.setDelay(new Duration(delay));
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
    return directionQueue.peek();
  }

  private void onTimerTriggers(ActionEvent actionEvent) {
    Direction direction = snake.getDirection();
    if (directionQueue.size() > 0) {
      direction = directionQueue.poll();
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
    for (CellDTO[] row : cellDTOS) {
      for (CellDTO cellDTO : row) {
        cellDTO.drawObjects();
      }
    }
  }

  private void onSnakeDestroyed() {
    timeline.stop();
    presenter.fillCanvas(cellDTOS, Color.RED);
    if (presenter.scoreLabel.getScene().getWindow() instanceof Stage stage) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/game_end.fxml"));
        stage.setScene(new Scene(loader.load()));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
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
