package ru.nsu.fit.oop.melnikov.game.snake.model;

import java.util.Timer;
import java.util.TimerTask;

import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeCrashedException;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class Game {

  private final Snake snake;
  private final Timer timer;
  private boolean crush;

  public Game(Snake snake) {
    this.snake = snake;
    this.timer = new Timer();
    crush = false;
  }

  public boolean isCrush() {
    return crush;
  }

  public void start() {
    timer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            try {
              snake.move();
            } catch (SnakeCrashedException e) {
              crush = true;
              this.cancel();
            }
          }
        }, 0, 200
    );
  }

  private void stop() {
    timer.cancel();
  }

}
