package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.NoPlaceForAppleException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeCrashedException;
import ru.nsu.fit.oop.melnikov.game.snake.model.exceptions.crash.SnakeInSnakeException;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ObservableSnake extends Snake {

    PropertyChangeSupport support;

    public ObservableSnake(Field field, List<SnakeNode> snakeNodes) throws SnakeInSnakeException {
        super(field, snakeNodes);
        support = new PropertyChangeSupport(this);
    }

    public ObservableSnake(Snake snake) throws SnakeInSnakeException {
        this(snake.getField(), snake.getNodes());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    protected void appendHead() throws SnakeCrashedException, NoPlaceForAppleException {
        super.appendHead();
        support.fireIndexedPropertyChange("snakeNodes", this.size() - 1, null, this.getHead());
    }

    @Override
    protected SnakeNode removeTail() {
        SnakeNode removedTail = super.removeTail();
        support.fireIndexedPropertyChange("snakeNodes", 0, removedTail, null);
        return removedTail;
    }
}
