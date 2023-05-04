package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell;

import java.util.Objects;
import javafx.beans.binding.NumberBinding;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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

  /**
   * Draws an object at specified context in specified place. This method should be overridden by
   * inheritors if it is needed an animation on object appearance. Makes come object appears without
   * animations by default.
   *
   * @param context where to draw
   * @param rect place on a canvas to place an image
   * @return Optional.empty() if there isn't any animation and both animation timer and timeline if
   *     an animation takes place
   */
  public void draw(GraphicsContext context, Rect<NumberBinding> rect) {
    context.setGlobalAlpha(1.0);
    context.drawImage(
            getImage(),
            rect.x().doubleValue(),
            rect.y().doubleValue(),
            rect.width().doubleValue(),
            rect.height().doubleValue());
  }
}
