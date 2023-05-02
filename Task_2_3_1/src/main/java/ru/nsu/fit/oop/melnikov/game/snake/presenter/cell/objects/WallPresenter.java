package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

import javafx.scene.image.Image;

import java.util.Objects;

public class WallPresenter implements CellObjectPresenter {

    @Override
    public Image getImage() {
    return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cell_object_assets/wall.png")));
    }
}
