package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

public class EmptyCellDTO extends CellObjectSmoothAnimationDTO {

  public EmptyCellDTO(String texturePackPath) {
    super(texturePackPath);
  }

  @Override
  protected String getImageName() {
    return "empty_cell.png";
  }
}
