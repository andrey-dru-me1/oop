package ru.nsu.fit.oop.melnikov.game.snake.presenter.settings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.utils.Resources;

public class GameSettings {

  public static final GameSettings INSTANCE = getInstance();

  @JsonProperty private final Map<KeyCode, SnakeKey> keys;
  @JsonProperty private int tickDelay;

  private GameSettings() {
    tickDelay = 100;
    keys =
        new HashMap<>(
            Map.of(
                KeyCode.UP, SnakeKey.UP,
                KeyCode.LEFT, SnakeKey.LEFT,
                KeyCode.RIGHT, SnakeKey.RIGHT,
                KeyCode.DOWN, SnakeKey.DOWN,
                KeyCode.ESCAPE, SnakeKey.MENU));
  }

  private static GameSettings getInstance() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      File file = new File(Resources.GAME_SETTINGS_FILE);
      if (file.isFile()) {
        return mapper.readValue(file, GameSettings.class);
      }
    } catch (IOException ignore) {
      // If there was an error in file accessing then program skip it and makes new GameSettings
      // instance
    }
    return new GameSettings();
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
