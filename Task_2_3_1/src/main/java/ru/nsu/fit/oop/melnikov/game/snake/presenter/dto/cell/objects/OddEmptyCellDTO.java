package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.EmptyCell;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTO;

public class OddEmptyCellDTO extends CellObjectDTO {

    public OddEmptyCellDTO(String texturePackPath) {
        super(texturePackPath);
    }

    @Override
    protected String getImageName() {
        return "odd_empty_cell.png";
    }

    @Override
    public boolean checkForCoincidence(Cell cell, CellObject cellObject) {
    return cellObject.getClass().equals(EmptyCell.class) && (cell.getX() + cell.getY()) % 2 == 1;
    }
}
