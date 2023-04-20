package ru.nsu.fit.oop.melnikov.game.snake.model;

import java.util.Timer;
import java.util.TimerTask;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.NoPlaceForAppleException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeCrashedException;
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
            try {
              snake.move();
            } catch (SnakeCrashedException e) {
              crash = true;
              this.cancel();
              whenCrashed.run();
            } catch(NoPlaceForAppleException e) {
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
