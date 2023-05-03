package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;



public class SnakeNodeDTO extends CellObjectSmoothAnimationDTO {

  public SnakeNodeDTO(String texturePackPath) {
    super(texturePackPath);
  }

  @Override
  protected String getImageName() {
    return "snake.png";
  }

}
