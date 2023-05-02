package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

import java.util.Objects;
import javafx.scene.image.Image;

public class EmptyCellPresenter implements CellObjectPresenter {

    private final String texturePath;

    public EmptyCellPresenter(String texturePackPath) {
        this.texturePath = texturePackPath + "/empty_cell.png";
    }

    @Override
    public Image getImage() {
    return new Image(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
    }
}
