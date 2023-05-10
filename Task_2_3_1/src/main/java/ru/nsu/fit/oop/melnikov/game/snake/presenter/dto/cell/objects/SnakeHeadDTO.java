package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTO;

public class SnakeHeadDTO extends CellObjectDTO {

    public SnakeHeadDTO(String texturePackPath) {
        super(texturePackPath);
    }

    @Override
    protected String getImageName() {
        return "snake_head.png";
    }

    @Override
    public boolean checkForCoincidence(Cell cell, CellObject cellObject) {
        if(cellObject instanceof SnakeNode snakeNode) {
            return snakeNode.snake().getHeadCell().equals(cell);
        }
        return false;
    }

}
