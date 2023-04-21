package ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects;

public interface CellObject {
  int getPriority();

  void onAnotherCellObjectAppearance(CellObject anotherCellObject);

}
