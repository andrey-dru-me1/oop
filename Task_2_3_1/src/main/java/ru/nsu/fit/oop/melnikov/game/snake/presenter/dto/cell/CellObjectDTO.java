package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell;

import java.util.Objects;
import javafx.beans.binding.NumberBinding;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
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

  public abstract boolean checkForCoincidence(Cell cell, CellObject cellObject);

  public Image getImage() {
    return image;
  }

  /**
   * Draws an object at specified context in specified place.
   *
   * @param context where to draw
   * @param rect place on a canvas to place an image
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
