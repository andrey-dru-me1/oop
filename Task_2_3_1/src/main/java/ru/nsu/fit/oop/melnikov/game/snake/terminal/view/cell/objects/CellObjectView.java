package ru.nsu.fit.oop.melnikov.game.snake.terminal.view.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;

public interface CellObjectView {

    char getSymbol();

    Class<? extends CellObject> getCellObjectClass();

}
