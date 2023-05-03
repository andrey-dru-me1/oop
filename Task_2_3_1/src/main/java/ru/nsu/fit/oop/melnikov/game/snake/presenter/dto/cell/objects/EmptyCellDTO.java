package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTO;

public class EmptyCellDTO extends CellObjectDTO {

  public EmptyCellDTO(String texturePackPath) {
    super(texturePackPath);
  }

  @Override
  protected String getImageName() {
    return "empty_cell.png";
  }
}
