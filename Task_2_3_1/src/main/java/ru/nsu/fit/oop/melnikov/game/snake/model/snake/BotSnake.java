package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.*;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Apple;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.EmptyCell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;

public class BotSnake extends Snake {

  private static final List<Direction> directions =
      List.of(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN);

  public BotSnake(Field field, List<SnakePoint> snakeIntPoints) {
    super(field, snakeIntPoints);
  }

  @Override
  public void move() {
    Direction res = this.getDirection();
    int max = 0;
    for (Direction direction : directions) {
      if (direction.isOpposite(this.getDirection())) continue;

      Cell cell = field.getCell(field.calculateNextPoint(body.getHeadPoint(), direction));
      int result = calculatePoints(10, new ArrayDeque<>(), cell);
      if(result > max) {
        max = result;
        res = direction;
      }
    }
    super.setDirection(res);
    super.move();
  }

  private Integer calculatePoints(Integer depth, Collection<Cell> markedCells, Cell cell) {
    if (depth <= 0 || markedCells.contains(cell) || cell.contains(Wall.class) || cell.contains(SnakeNode.class))
      return 0;
    markedCells.add(cell);

    Integer score = 0;
    if (cell.contains(Apple.class)) score += 2;
    else if (cell.contains(EmptyCell.class)) score += 1;

    for (Direction direction : directions) {
      Cell nextCell = field.getCell(field.calculateNextPoint(cell, direction));
      score += calculatePoints(depth - 1, markedCells, nextCell);
    }

    markedCells.remove(cell);
    return score;
  }
}
