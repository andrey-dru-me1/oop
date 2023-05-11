package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.CellDTO;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters.game.GameScreenPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.settings.GameSettings;

public class Game {

  private final Snake snake;
  private final GameScreenPresenter presenter;
  private final Deque<Direction> directionQueue;
  private final GameSettings gameSettings;
  private final GameState gameState;
  private CellDTO[][] cellDTOS;
  private Timeline timeline;
  private KeyFrame keyFrame;

  public Game(Snake snake, GameSettings gameSettings, GameScreenPresenter presenter) {
    this.snake = snake;
    this.gameState = new GameState(snake.size());
    this.gameSettings = gameSettings;
    this.timeline = new Timeline();
    this.timeline.setCycleCount(Animation.INDEFINITE);
    this.directionQueue = new ArrayDeque<>(2);
    directionQueue.add(snake.getDirection());
    this.presenter = presenter;
    timeline.setDelay(new Duration(gameSettings.getTickDelay()));
  }

  public GameState getGameState() {
    return gameState;
  }

  public void setCellDTOS(CellDTO[][] cellDTOS) {
    this.cellDTOS = cellDTOS;
  }

  public void setDelay(int millisDelay) {
    this.gameSettings.setTickDelay(millisDelay);
    keyFrame = new KeyFrame(new Duration(millisDelay), this::onTimerTriggers);
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
    keyFrame = new KeyFrame(new Duration(gameSettings.getTickDelay()), this::onTimerTriggers);
    timeline.getKeyFrames().add(keyFrame);
    timeline.playFrom(new Duration(gameSettings.getTickDelay()));
    gameState.setStatus(GameState.Status.RUNNING);
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

    gameState.setScore(snake.getScore());
    gameState.setPlayerSnakeLength(snake.size());

    if (snake.isDestroyed()) {
      onSnakeDestroyed();
      return;
    }
    if (snake.getField().isNoPlaceForApple()) {
      onWin();
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
    gameState.setStatus(GameState.Status.LOSE);
    timeline.stop();
    presenter.onGameEnd();
  }

  private void onWin() {
    gameState.setStatus(GameState.Status.WIN);
    stop();
    presenter.onGameEnd();
  }

  public void pause() {
    timeline.pause();
    gameState.setStatus(GameState.Status.PAUSED);
  }

  public void play() {
    setDelay(gameSettings.getTickDelay());
    timeline.playFrom(new Duration(gameSettings.getTickDelay()));
    gameState.setStatus(GameState.Status.RUNNING);
  }

  public void stop() {
    timeline.stop();
  }
}
