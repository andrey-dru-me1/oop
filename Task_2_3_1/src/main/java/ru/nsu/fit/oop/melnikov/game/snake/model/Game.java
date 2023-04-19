package ru.nsu.fit.oop.melnikov.game.snake.model;

import java.util.Timer;
import java.util.TimerTask;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeCrashedException;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class Game {

  private final Snake snake;
  private final Timer timer;
  private final int delay;
  private boolean crash;
  private final Runnable whenCrashed;

  public Game(Snake snake, int delay) {
    this(snake, delay, () -> {});
  }

  public Game(Snake snake, int delay, Runnable whenCrashed) {
    this.snake = snake;
    this.delay = delay;
    this.whenCrashed = whenCrashed;
    this.timer = new Timer();
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
            try {
              snake.move();
            } catch (SnakeCrashedException e) {
              crash = true;
              this.cancel();
              whenCrashed.run();
            }
          }
        },
        delay,
        delay);
  }

  private void stop() {
    timer.cancel();
  }

}
