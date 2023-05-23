package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

/** Empty cell is more like floor: it is just default object which must be on every cell. */
public class EmptyCell implements CellObject {
  /**
   * Lowest possible priority.
   *
   * @return object priority
   */
  @Override
  public int getPriority() {
    return 0;
  }

  /**
   * There is nothing to do when something appears on empty cell
   *
   * @param anotherCellObject new object on current cell
   */
  @Override
  public void onAnotherCellObjectAppearance(CellObject anotherCellObject) {
    // Makes no effect
  }
}
