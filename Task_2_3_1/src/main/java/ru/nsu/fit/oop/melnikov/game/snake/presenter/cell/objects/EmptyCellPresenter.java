package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

import java.util.Objects;
import javafx.scene.image.Image;

public class EmptyCellPresenter implements CellObjectPresenter {

    @Override
    public Image getImage() {
    return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cell_object_assets/empty_cell.png")));
    }
}
