package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

import java.util.Objects;
import javafx.scene.image.Image;

public class SnakeNodePresenter implements CellObjectPresenter {

    private final String texturePath;

    public SnakeNodePresenter(String texturePackPath) {
        this.texturePath = texturePackPath + "/snake.png";
    }

    @Override
    public Image getImage() {
    return new Image(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
    }
}
