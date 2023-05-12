package ru.nsu.fit.oop.melnikov.game.snake.presenter.utils;

import java.net.URL;

public enum FXMLScreen {
  CHANGE_KEYS("/settings/change_keys.fxml"),
  GAME_END("/game/game_end.fxml"),
  GAME_SCREEN("/game/game_screen.fxml"),
  MAIN_MENU("/menu/main_menu.fxml"),
  SELECT_MAP("/menu/select_map.fxml"),
  SETTINGS("/settings/settings.fxml"),
  LISTEN_KEY("/settings/listen_key.fxml");

  private final URL screen;

  FXMLScreen(String filePath) {
    screen = getClass().getResource(Resources.FXMLS_DIR + filePath);
  }

  public URL getScreenUrl() {
    return screen;
  }
}
