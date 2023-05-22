package ru.nsu.fit.oop.melnikov.game.snake.terminal.view.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;

public class WallView implements CellObjectView {
    @Override
    public Class<? extends CellObject> getCellObjectClass() {
        return Wall.class;
    }

    @Override
    public char getSymbol() {
        return '#';
    }
}
