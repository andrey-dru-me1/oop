package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.CellObjectDTO;

public class SnakeNodeDTO extends CellObjectDTO {

  public SnakeNodeDTO(String texturePackPath) {
    super(texturePackPath);
  }

  @Override
  protected String getImageName() {
    return "snake.png";
  }

  @Override
  public boolean checkForCoincidence(Cell cell, CellObject cellObject) {
    return cellObject.getClass().equals(SnakeNode.class);
  }
}
