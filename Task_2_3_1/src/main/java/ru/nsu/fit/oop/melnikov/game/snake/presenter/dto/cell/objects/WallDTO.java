package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTO;

public class WallDTO extends CellObjectDTO {

    public WallDTO(String texturePackPath) {
        super(texturePackPath);
    }

    @Override
    protected String getImageName() {
        return "wall.png";
    }
}
