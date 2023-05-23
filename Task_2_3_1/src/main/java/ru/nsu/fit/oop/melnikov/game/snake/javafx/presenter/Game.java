package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter;

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.CellDTO;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.game.GameScreenPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

/** Represents game data. */
public class Game {

  private final Field field;
  private final Snake snake;
  private final GameScreenPresenter presenter;
  private final Deque<Direction> directionQueue;
  private final GameState gameState;
  private CellDTO[][] cellDTOS;
  private Timeline timeline;
  private KeyFrame keyFrame;

  public Game(Field field, Snake snake, GameScreenPresenter presenter, String mapName) {
    this.field = field;
    this.snake = snake;
    this.gameState = new GameState(snake.size(), mapName);
    this.timeline = new Timeline();
    this.timeline.setCycleCount(Animation.INDEFINITE);
    this.directionQueue = new ArrayDeque<>(2);
    this.presenter = presenter;
    timeline.setDelay(new Duration(GameSettings.INSTANCE.getTickDelay()));
  }

  public GameState getGameState() {
    return gameState;
  }

  public void setCellDTOS(CellDTO[][] cellDTOS) {
    this.cellDTOS = cellDTOS;
  }

  public void setDelay(int millisDelay) {
    GameSettings.INSTANCE.setTickDelay(millisDelay);
    keyFrame = new KeyFrame(new Duration(millisDelay), this::onTimerTriggers);
    timeline.stop();
    timeline = new Timeline();
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.getKeyFrames().add(keyFrame);
  }

  /**
   * Appends direction to the queue of directions, so user can choose a route quicker than snake
   * moves. Also compares with previous direction, so new direction must be orthogonally for the
   * previous.
   *
   * @param direction direction for snake to move
   */
  public void addDirection(Direction direction) {
    if (!directionQueue.isEmpty()
        && (direction.isOpposite(directionQueue.peekLast())
            || direction.equals(directionQueue.peek()))) {
      return;
    }
    this.directionQueue.add(direction);
  }

  /**
   * Method is delegated to the field.
   *
   * @param appleCount count of apples to generate.
   */
  public void regenerateApples(int appleCount) {
    field.regenerateApples(appleCount);
  }

  /** Make the game begin. */
  public void start() {
    keyFrame =
        new KeyFrame(new Duration(GameSettings.INSTANCE.getTickDelay()), this::onTimerTriggers);
    timeline.getKeyFrames().add(keyFrame);
    timeline.playFrom(new Duration(GameSettings.INSTANCE.getTickDelay()));
    gameState.setStatus(GameState.Status.RUNNING);
  }

  public Direction getDirection() {
    return snake.getDirection();
  }

  /**
   * Called each tick of game time.
   *
   * @param actionEvent ignored
   */
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

  /** Redraw all the field. */
  public void redraw() {
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
    setDelay(GameSettings.INSTANCE.getTickDelay());
    timeline.playFrom(new Duration(GameSettings.INSTANCE.getTickDelay()));
    gameState.setStatus(GameState.Status.RUNNING);
  }

  public void stop() {
    if (timeline != null) {
      timeline.stop();
    }
  }
}
