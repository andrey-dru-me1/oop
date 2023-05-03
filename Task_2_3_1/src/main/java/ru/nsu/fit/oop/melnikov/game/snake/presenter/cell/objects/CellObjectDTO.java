package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

import java.util.Objects;
import java.util.Optional;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.Rect;

public abstract class CellObjectDTO {

  protected final Image image;
  protected final String texturePackPath;

  protected CellObjectDTO(String texturePackPath) {
    this.texturePackPath = texturePackPath;
    String texturePath = texturePackPath + "/" + getImageName();
    this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
  }

  protected abstract String getImageName();

  public Image getImage() {
    return image;
  }

  public Optional<Pair<AnimationTimer, Timeline>> draw(GraphicsContext context, Rect<Double> rect) {
    context.setGlobalAlpha(1.0);
    context.drawImage(getImage(), rect.x(), rect.y(), rect.width(), rect.height());
    return Optional.empty();
  }
}
