package ru.nsu.fit.oop.melnikov.game.snake.presenter;

public class GameState {
  private final String mapName;
  private int score;
  private int playerSnakeLength;
  private Status status;
  public GameState(int playerSnakeLength, String mapName) {
    this.mapName = mapName;
    this.playerSnakeLength = playerSnakeLength;
    this.score = 0;
    this.status = Status.PAUSED;
  }

  public String getMapName() {
    return mapName;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getPlayerSnakeLength() {
    return playerSnakeLength;
  }

  public void setPlayerSnakeLength(int playerSnakeLength) {
    this.playerSnakeLength = playerSnakeLength;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public enum Status {
    RUNNING,
    PAUSED,
    WIN,
    LOSE
  }
}
