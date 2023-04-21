package ru.nsu.fit.oop.melnikov.game.snake.model;

import java.util.Timer;
import java.util.TimerTask;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class Game {

  private final Snake snake;
  private final Timer timer;
  private final int delay;
  private final Runnable whenCrashed;
  private final Runnable whenWon;
  private boolean crash;

  public Game(Snake snake, int delay) {
    this(snake, delay, () -> {}, () -> {});
  }

  public Game(Snake snake, int delay, Runnable whenCrashed, Runnable whenWon) {
    this.snake = snake;
    this.delay = delay;
    this.whenCrashed = whenCrashed;
    this.timer = new Timer();
    this.whenWon = whenWon;
    crash = false;
  }

  public boolean isCrash() {
    return crash;
  }

  public void start() {
    timer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            snake.move();
            if (snake.isDestroyed()) {
              crash = true;
              this.cancel();
              whenCrashed.run();
            }
            if (snake.getField().isNoPlaceForApple()) {
              this.cancel();
              whenWon.run();
            }
          }
        },
        delay,
        delay);
  }

  public void stop() {
    timer.cancel();
    timer.purge();
  }
}
