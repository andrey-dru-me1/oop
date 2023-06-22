package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.utils;

import java.util.Collection;
import java.util.List;

public class TextureName {
  private static final Collection<String> TEXTURE_DIRECTORIES = List.of(
          "minecraft", "minimalism"
  );

  private TextureName() {}

  public static Collection<String> getTextureDirectories() {
    return TEXTURE_DIRECTORIES;
  }

}
