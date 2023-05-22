package ru.nsu.fit.oop.melnikov.game.snake.terminal.view.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;

public class SnakeNodeView implements CellObjectView {
    @Override
    public Class<? extends CellObject> getCellObjectClass() {
        return SnakeNode.class;
    }

    @Override
    public char getSymbol() {
        return 'o';
    }
}
