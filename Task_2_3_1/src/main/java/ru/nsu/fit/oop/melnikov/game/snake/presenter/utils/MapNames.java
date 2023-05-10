package ru.nsu.fit.oop.melnikov.game.snake.presenter.utils;

import java.util.Collection;
import java.util.List;

public class MapNames {

    private static final Collection<String> MAP_FILENAMES = List.of(
            "big_map.txt",
            "default.txt",
            "default_walls.txt",
            "test.txt"
    );

    private MapNames() {}

    public static Collection<String> getMapFilenames() {
        return MAP_FILENAMES;
    }

}
