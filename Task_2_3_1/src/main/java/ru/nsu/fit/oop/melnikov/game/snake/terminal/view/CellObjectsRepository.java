package ru.nsu.fit.oop.melnikov.game.snake.terminal.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.terminal.view.cell.objects.*;

public class CellObjectsRepository {

  public static final CellObjectsRepository INSTANCE = new CellObjectsRepository();

  private final Collection<CellObjectView> cellObjectViews =
      List.of(new AppleView(), new EmptyCellView(), new SnakeNodeView(), new WallView());
  private final Map<Class<? extends CellObject>, CellObjectView> map;

  private CellObjectsRepository() {
    map = new HashMap<>();
    for (CellObjectView cellObjectView : cellObjectViews) {
      map.put(cellObjectView.getCellObjectClass(), cellObjectView);
    }
  }

  public Collection<CellObjectView> getCellObjectViews() {
    return cellObjectViews;
  }

  public CellObjectView getCellObjectView(Class<? extends CellObject> cellObjectClass) {
    return map.get(cellObjectClass);
  }
}
