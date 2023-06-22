package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.objects;

import javafx.beans.binding.NumberBinding;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.Game;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.Rect;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.CellObjectDTO;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;

public class SnakeHeadDTO extends CellObjectDTO {

  public SnakeHeadDTO(String texturePackPath) {
    super(texturePackPath);
  }

  @Override
  protected String getImageName() {
    return "snake_head.png";
  }

  @Override
  public boolean checkForCoincidence(Cell cell, CellObject cellObject) {
    if (cellObject instanceof SnakeNode snakeNode) {
      return snakeNode.snake().getHeadCell().equals(cell);
    }
    return false;
  }

/**
* Draws snake head rotated according to the current snake direction.
 *
 * @param context where to draw
 * @param game all the information required for knowing how to draw an object
 * @param rect place on a canvas to place an image
*/
  @Override
  public void draw(GraphicsContext context, Game game, Rect<NumberBinding> rect) {
    Canvas canvas = new Canvas(rect.width().doubleValue(), rect.height().doubleValue());
    canvas.getGraphicsContext2D().setImageSmoothing(false);
    canvas
        .getGraphicsContext2D()
        .drawImage(image, 0, 0, rect.width().doubleValue(), rect.height().doubleValue());
    canvas.setRotate(directionToRotation(game.getDirection()));

    SnapshotParameters params = new SnapshotParameters();
    params.setFill(Color.TRANSPARENT);
    context.setGlobalAlpha(1.0);
    context.drawImage(
        canvas.snapshot(params, null),
        rect.x().doubleValue(),
        rect.y().doubleValue(),
        rect.width().doubleValue(),
        rect.height().doubleValue());
  }

/**
* Helper method that converts direction to an angle to rotate image to.
 * @param direction direction where a snake is looking to
 * @return angle on which image should be rotated
*/
  private double directionToRotation(Direction direction) {
    return switch (direction) {
      case UP -> 0;
      case DOWN -> 180;
      case LEFT -> -90;
      case RIGHT -> 90;
    };
  }
}
