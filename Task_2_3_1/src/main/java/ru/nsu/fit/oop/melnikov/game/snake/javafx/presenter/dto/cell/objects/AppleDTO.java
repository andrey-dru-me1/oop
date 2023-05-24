package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.CellObjectDTO;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Apple;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;

public class AppleDTO extends CellObjectDTO {

    public AppleDTO(String texturePackPath) {
        super(texturePackPath);
    }

    @Override
    protected String getImageName() {
        return "apple.png";
    }

    @Override
    public boolean checkForCoincidence(Cell cell, CellObject cellObject) {
        return cellObject.getClass().equals(Apple.class);
    }

}
