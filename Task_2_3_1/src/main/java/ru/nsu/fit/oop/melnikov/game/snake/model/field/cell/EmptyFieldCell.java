package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell;

import java.util.Optional;

import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class EmptyFieldCell extends FieldCell {

  private boolean hasApple;
  private Optional<Snake> snake;

  public EmptyFieldCell(int x, int y) {
    super(x, y);
    hasApple = false;
    snake = Optional.empty();
  }

  public boolean hasApple() {
    return hasApple;
  }

  public Optional<Snake> getSnake() {
    return snake;
  }

  public boolean hasSnake() {
    return snake.isPresent();
  }

  public void putApple() {
    support.firePropertyChange("apple", this.hasApple, true);
    this.hasApple = true;
  }

  public void eatApple() {
    support.firePropertyChange("apple", this.hasApple, false);
    this.hasApple = false;
  }

  public void putSnake(Snake snake) {
    support.firePropertyChange("snake", this.snake, snake);
    this.snake = Optional.of(snake);
  }

  public void moveSnake() {
    support.firePropertyChange("snake", this.snake, Optional.empty());
    this.snake = Optional.empty();
  }

}
