package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.settings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.CellObjectDTOSRepository;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils.Resources;

/**
* Contains all the game settings.
*/
public class GameSettings {

  public static final GameSettings INSTANCE = getInstance();

  @JsonProperty private final Map<KeyCode, SnakeKey> keys;
  @JsonProperty private int tickDelay;
  @JsonProperty private String textureName;
  @JsonProperty private int applesPercentage;
  @JsonIgnore private CellObjectDTOSRepository repository;

  private GameSettings() {
    tickDelay = 100;
    applesPercentage = 20;
    keys =
        new HashMap<>(
            Map.of(
                KeyCode.UP, SnakeKey.UP,
                KeyCode.LEFT, SnakeKey.LEFT,
                KeyCode.RIGHT, SnakeKey.RIGHT,
                KeyCode.DOWN, SnakeKey.DOWN,
                KeyCode.ESCAPE, SnakeKey.MENU));
    textureName = "minecraft";
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

  public void setRepository(CellObjectDTOSRepository repository) {
    this.repository = repository;
  }

  public String getTextureName() {
    return textureName;
  }

  public void setTextureName(String textureName) {
    this.textureName = textureName;
    if (repository != null) {
      repository.onTextureChange(textureName);
    }
  }

  public int getTickDelay() {
    return tickDelay;
  }

  public void setTickDelay(int tickDelay) {
    this.tickDelay = tickDelay;
  }

  public int getApplesPercentage() {
    return applesPercentage;
  }

  public void setApplesPercentage(int applesPercentage) {
    this.applesPercentage = applesPercentage;
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
