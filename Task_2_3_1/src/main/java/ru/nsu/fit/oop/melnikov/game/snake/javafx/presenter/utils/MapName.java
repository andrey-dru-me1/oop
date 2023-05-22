package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils;

import java.util.Collection;
import java.util.List;

public class MapName {

    private static final Collection<String> MAP_FILENAMES = List.of(
            "big_map.txt",
            "default.txt",
            "default_walls.txt",
            "test.txt",
            "big_empty_map.txt"
    );

    private MapName() {}

    public static Collection<String> getMapFilenames() {
        return MAP_FILENAMES;
    }

}
