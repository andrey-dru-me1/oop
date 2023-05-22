package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.CellObjectDTO;

public class WallDTO extends CellObjectDTO {

    public WallDTO(String texturePackPath) {
        super(texturePackPath);
    }

    @Override
    protected String getImageName() {
        return "wall.png";
    }

    @Override
    public boolean checkForCoincidence(Cell cell, CellObject cellObject) {
        return cellObject.getClass().equals(Wall.class);
    }

}
