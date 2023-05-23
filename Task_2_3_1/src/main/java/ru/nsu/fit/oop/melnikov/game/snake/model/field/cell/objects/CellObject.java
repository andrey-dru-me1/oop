package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

/**
 * Cell objects are objects that can be placed on cell, such as wall, snake node, apple and even
 * empty cell. They are strongly connected to the cell, so the field looks like set of cells with
 * stack of cell objects on each of it.
 *
 * <p>Objects can appear and disappear during the game.
 *
 * <p>Each type of object has its own priority: the more priority, the higher level object has. This
 * influences on drawing, for example.
 */
public interface CellObject {

  /**
   * Returns the priority of current cell object. The more priority, the higher object is located on
   * field's Z axis.
   *
   * <p>Default snake has priority variance from 0 (empty cell) up to 100 (wall), but new custom
   * cell objects can have either less or more.
   *
   * @return priority of current object
   */
  int getPriority();

  /**
   * Method is called when another object appears on cell where current object is located. This
   * might be using, for example, when snake node appears on cell with apple, so this method allows
   * apple to react on snake node appearance.
   *
   * @param anotherCellObject new object on current cell
   */
  void onAnotherCellObjectAppearance(CellObject anotherCellObject);
}
