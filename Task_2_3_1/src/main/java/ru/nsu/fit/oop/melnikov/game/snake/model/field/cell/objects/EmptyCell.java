package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

public class EmptyCell implements CellObject {
    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void onAnotherCellObjectAppearance(CellObject anotherCellObject) {
        // Makes no effect
    }
}
