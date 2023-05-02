package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

import java.util.Objects;
import javafx.scene.image.Image;

public class WallPresenter implements CellObjectPresenter {

    private final String texturePath;

    public WallPresenter(String texturePackPath) {
        this.texturePath = texturePackPath + "/wall.png";
    }

    @Override
    public Image getImage() {
    return new Image(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
    }
}
