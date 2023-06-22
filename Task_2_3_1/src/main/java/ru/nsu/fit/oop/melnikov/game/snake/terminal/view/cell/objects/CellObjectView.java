package ru.nsu.fit.oop.melnikov.game.snake.terminal.view.cell.objects;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;

public interface CellObjectView {

  /**
   * Symbol to show on terminal window.
   *
   * @return char symbol connected with current cell object
   */
  char getSymbol();

/**
* Returns cell object which is connected with current cell object view.
 * @return connected cell object
*/
  Class<? extends CellObject> getCellObjectClass();
}
