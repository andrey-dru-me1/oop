package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.CellDTO;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.presenters.game.GameScreenPresenter;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings.GameSettings;
import ru.nsu.fit.oop.melnikov.game.snake.model.GameData;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

/** Represents game data. */
public class Game {

  private final GameData gameData;
  private final GameScreenPresenter presenter;
  private final Deque<Direction> directionQueue;
  private final GameState gameState;
  private CellDTO[][] cellDTOS;
  private Timeline timeline;
  private KeyFrame keyFrame;

  public Game(GameData gameData, GameScreenPresenter presenter, String mapName) {
    this.gameData = gameData;
    this.gameState = new GameState(gameData.snakes().get(0).size(), mapName);
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
    keyFrame = new KeyFrame(new Duration(millisDelay), actionEvent -> onTimerTriggers());
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
    gameData.field().regenerateApples(appleCount);
  }

  /** Make the game begin. */
  public void start() {
    keyFrame =
        new KeyFrame(
            new Duration(GameSettings.INSTANCE.getTickDelay()), actionEvent -> onTimerTriggers());
    timeline.getKeyFrames().add(keyFrame);
    timeline.playFrom(new Duration(GameSettings.INSTANCE.getTickDelay()));
    gameState.setStatus(GameState.Status.RUNNING);
  }

  public Direction getDirection() {
    return gameData.snakes().get(0).getDirection();
  }

  /** Called each tick of game time. */
  private void onTimerTriggers() {
    Snake playerSnake = gameData.snakes().get(0);
    Direction direction = playerSnake.getDirection();
    if (directionQueue.size() > 0) {
      direction = directionQueue.poll();
      if (direction.isOpposite(playerSnake.getDirection())) {
        direction = playerSnake.getDirection();
      }
    }
    if (playerSnake.isDestroyed()) {
      onSnakeDestroyed();
      return;
    }
    if (playerSnake.getField().isNoPlaceForApple()) {
      onWin();
      return;
    }
    playerSnake.move(direction);
    presenter.setScore(playerSnake.getScore());

    gameState.setScore(playerSnake.getScore());
    gameState.setPlayerSnakeLength(playerSnake.size());


    Queue<Snake> snakesToRemove = new ArrayDeque<>();
    for (int i = 1; i < gameData.snakes().size(); i++) {
      Snake snake = gameData.snakes().get(i);
      if (!snake.isDestroyed()) snake.move();
      if (snake.isDestroyed()) {
        snakesToRemove.add(snake);
      }
    }

    for (Snake snake : snakesToRemove) {
      gameData.snakes().remove(snake);
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
