package ru.nsu.fit.oop.melnikov.game.snake.presenter.settings;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;

public class GameSettings {

    private final Map<KeyCode, SnakeKey> keys;
    private int tickDelay;

    public GameSettings() {
        tickDelay = 100;
        keys = new HashMap<>(Map.of(
                KeyCode.UP, SnakeKey.UP,
                KeyCode.LEFT, SnakeKey.LEFT,
                KeyCode.RIGHT, SnakeKey.RIGHT,
                KeyCode.DOWN, SnakeKey.DOWN,
                KeyCode.ESCAPE, SnakeKey.MENU
        ));
    }

    public int getTickDelay() {
        return tickDelay;
    }

    public void setTickDelay(int tickDelay) {
        this.tickDelay = tickDelay;
    }

    public Map<KeyCode, SnakeKey> getKeys() {
        return keys;
    }

    public SnakeKey getSnakeKey(KeyCode key) {
        return keys.get(key);
    }

    public void putKey(KeyCode keyCode, SnakeKey key) {
        keys.put(keyCode, key);
    }
}
