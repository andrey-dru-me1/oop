package ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.javafx.presenter.dto.cell.CellObjectDTO;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.EmptyCell;

public class EmptyCellDTO extends CellObjectDTO {

  public EmptyCellDTO(String texturePackPath) {
    super(texturePackPath);
  }

  @Override
  protected String getImageName() {
    return "empty_cell.png";
  }

  @Override
  public boolean checkForCoincidence(Cell cell, CellObject cellObject) {
    return cellObject.getClass().equals(EmptyCell.class);
  }
}
