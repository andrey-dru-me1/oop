package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

public class WallDTO extends CellObjectDTO {

    public WallDTO(String texturePackPath) {
        super(texturePackPath);
    }

    @Override
    protected String getImageName() {
        return "wall.png";
    }
}
