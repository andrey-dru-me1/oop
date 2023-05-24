package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell;

import java.util.Objects;
import javafx.beans.binding.NumberBinding;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.Game;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.Rect;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;

/**
 * Represents object which is places on cell from model with parameters needed for javafx.
 * Responsible for object drawing with chosen texture pack.
 */
public abstract class CellObjectDTO {

  protected final Image image;

  protected CellObjectDTO(String texturePackPath) {
    String texturePath = texturePackPath + "/" + getImageName();
    this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
  }

  protected abstract String getImageName();

  /**
   * Returns true if such an object satisfies specific conditions and false otherwise.
   *
   * @param cell cell on which the object is places
   * @param cellObject specific cell object to check
   * @return true if such an object satisfies specific conditions and false otherwise
   */
  public abstract boolean checkForCoincidence(Cell cell, CellObject cellObject);

  public Image getImage() {
    return image;
  }

  /**
   * Draws an object at specified context in specified place.
   *
   * @param context where to draw
   * @param game all the information required for knowing how to draw an object (at overridden
   *     methods)
   * @param rect place on a canvas to place an image
   */
  public void draw(GraphicsContext context, Game game, Rect<NumberBinding> rect) {
    context.setGlobalAlpha(1.0);
    context.drawImage(
        getImage(),
        rect.x().doubleValue(),
        rect.y().doubleValue(),
        rect.width().doubleValue(),
        rect.height().doubleValue());
  }
}
