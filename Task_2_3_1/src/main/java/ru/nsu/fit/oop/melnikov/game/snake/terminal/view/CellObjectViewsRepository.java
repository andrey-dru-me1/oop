package ru.nsu.fit.oop.melnikov.game.snake.terminal.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.terminal.view.cell.objects.*;

/** This class contains all the cell object views, so you can simply get it from INSTANCE. */
public class CellObjectViewsRepository {

  public static final CellObjectViewsRepository INSTANCE = new CellObjectViewsRepository();

  private final Collection<CellObjectView> cellObjectViews =
      List.of(new AppleView(), new EmptyCellView(), new SnakeNodeView(), new WallView());
  private final Map<Class<? extends CellObject>, CellObjectView> map;

  private CellObjectViewsRepository() {
    map = new HashMap<>();
    for (CellObjectView cellObjectView : cellObjectViews) {
      map.put(cellObjectView.getCellObjectClass(), cellObjectView);
    }
  }

  /**
   * Returns cell object view found by specified cell object class.
   *
   * @param cellObjectClass class of cell object whose cell object view to search
   * @return cell object view connected to the specified cell object class
   */
  public CellObjectView getCellObjectView(Class<? extends CellObject> cellObjectClass) {
    return map.get(cellObjectClass);
  }
}
