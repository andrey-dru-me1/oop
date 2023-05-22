package ru.nsu.fit.oop.melnikov.game.snake.terminal.view.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Apple;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;

public class AppleView implements CellObjectView {
    @Override
    public Class<? extends CellObject> getCellObjectClass() {
        return Apple.class;
    }

    @Override
    public char getSymbol() {
        return 'a';
    }
}
