package ru.nsu.fit.oop.melnikov.game.snake.presenter.presenters;

public class GameState {
    private int score;
  private int playerSnakeLength;
  private Status status;
    public GameState(int playerSnakeLength) {
        this.playerSnakeLength = playerSnakeLength;
        this.score = 0;
        this.status = Status.PAUSED;
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
