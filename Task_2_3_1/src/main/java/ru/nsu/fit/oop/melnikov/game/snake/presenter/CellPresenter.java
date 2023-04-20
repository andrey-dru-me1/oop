package ru.nsu.fit.oop.melnikov.game.snake.presenter;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.EmptyFieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.FieldCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class CellPresenter implements PropertyChangeListener {
    private final Rectangle rect;

    public CellPresenter(FieldCell cell, Rectangle rect) {
        this.rect = rect;
        if(cell instanceof Wall) {
            rect.setFill(Color.BLACK);
        }
        else if(cell instanceof EmptyFieldCell emptyCell) {
            if(emptyCell.hasSnake()) {
                rect.setFill(Color.GREEN);
            }
            else if(emptyCell.hasApple()){
                rect.setFill(Color.RED);
            }
            else {
                rect.setFill(Color.WHITE);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case "snake" -> {
            if (evt.getNewValue() instanceof Optional<?>) {
                rect.setFill(Color.WHITE);
            } else if (evt.getNewValue() instanceof Snake) {
                rect.setFill(Color.GREEN);
            }
        }
        case "apple" -> {
            if (evt.getNewValue() instanceof Boolean res) {
                rect.setFill(Boolean.TRUE.equals(res) ? Color.RED : Color.GREEN);
            }
        }
        default -> {
            // No need to do anything on another keyboard keys
        }
    }
    }
}
