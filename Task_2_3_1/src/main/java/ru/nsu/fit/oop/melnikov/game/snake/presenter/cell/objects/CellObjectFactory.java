package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

import java.util.Map;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.*;

public class CellObjectFactory {

  private final Map<Class<? extends CellObject>, CellObjectPresenter> map;

  public CellObjectFactory(String texturePack) {
    String texturePackPath = "/textures/" + texturePack;
    map = Map.of(
            Wall.class, new WallPresenter(texturePackPath),
            EmptyCell.class, new EmptyCellPresenter(texturePackPath),
            SnakeNode.class, new SnakeNodePresenter(texturePackPath),
            Apple.class, new ApplePresenter(texturePackPath));
  }

  public <T extends CellObject> CellObjectPresenter create(T cellObject) {
    return map.get(cellObject.getClass());
  }
}
