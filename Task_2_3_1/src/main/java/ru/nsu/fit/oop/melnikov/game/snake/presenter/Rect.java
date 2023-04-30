package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import ru.nsu.fit.oop.melnikov.game.snake.model.point.Point;

public record Rect<T>(Point<T> p1, Point<T> p2) {}
