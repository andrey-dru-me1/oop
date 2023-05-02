package ru.nsu.fit.oop.melnikov.game.snake.presenter.cell.objects;

import java.util.Map;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.*;

public class CellObjectFactory {

  private static final Map<Class<? extends CellObject>, CellObjectPresenter> map =
      Map.of(
          Wall.class, new WallPresenter(),
          EmptyCell.class, new EmptyCellPresenter(),
          SnakeNode.class, new SnakeNodePresenter(),
          Apple.class, new ApplePresenter());

  public static <T extends CellObject> CellObjectPresenter create(T cellObject) {
    return map.get(cellObject.getClass());
  }
}
