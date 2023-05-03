package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTO;

public class SnakeNodeDTO extends CellObjectDTO {

  public SnakeNodeDTO(String texturePackPath) {
    super(texturePackPath);
  }

  @Override
  protected String getImageName() {
    return "snake.png";
  }
}
