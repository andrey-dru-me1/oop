package ru.nsu.fit.oop.melnikov.game.snake.model.point;

import java.util.Objects;

public class Point<T> {
    private final T x;
    private final T y;

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o instanceof Point<?> point) {
            return x == point.x && y == point.y;
        }
        return false;
    }}
