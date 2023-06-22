package ru.nsu.fit.oop.melnikov.game.snake.model.snake.interfaces;

import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;

public interface Movable {

    void move();

    void move(Direction direction);

}
