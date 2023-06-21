package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.*;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Apple;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;

public class BotSnake extends Snake {

  private static final List<Direction> directions =
      new ArrayList<>(List.of(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN));

  public BotSnake(Field field, List<SnakePoint> snakeIntPoints) {
    super(field, snakeIntPoints);
  }

  @Override
  public void move() {
    super.setDirection(bfs(this.getHeadCell()));
    super.move();
  }

  private Direction bfs(Cell cell) {
    Collection<Cell> markedCells = new ArrayDeque<>();
    Map<Cell, Direction> ways = new HashMap<>();

    PriorityQueue<GrayCell> grayCells =
        new PriorityQueue<>(Comparator.comparingInt(GrayCell::priority));
    GrayCell current = new GrayCell(cell, this.getDirection().getOpposite(), 0);
    grayCells.add(current);
    while (!current.cell.contains(Apple.class) && !grayCells.isEmpty()) {
      current = grayCells.poll();

      Collections.shuffle(directions);
      for (Direction direction : directions) {
        if (direction != current.from()) {
          Cell nextCell = field.getCell(field.calculateNextPoint(current.cell(), direction));
          Integer dPriority = nextCell.contains(Apple.class) ? 0 : 15;
          GrayCell nextGrayCell =
              new GrayCell(nextCell, direction.getOpposite(), current.priority() + dPriority);
          if (markedCells.contains(nextCell)
              || grayCells.contains(nextGrayCell)
              || nextCell.contains(Wall.class)
              || nextCell.contains(SnakeNode.class)) continue;
          grayCells.add(nextGrayCell);
          ways.put(nextCell, direction.getOpposite());
        }
      }

      markedCells.add(current.cell());
    }

    Cell currentReverse = current.cell();
    Direction direction = ways.get(currentReverse);
    while (currentReverse != cell) {
      direction = ways.get(currentReverse);
      currentReverse = field.getCell(field.calculateNextPoint(currentReverse, direction));
    }
    return direction.getOpposite();
  }

  private record GrayCell(Cell cell, Direction from, Integer priority) {
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      GrayCell grayCell = (GrayCell) o;

      return Objects.equals(cell, grayCell.cell);
    }

    @Override
    public int hashCode() {
      return cell != null ? cell.hashCode() : 0;
    }
  }
}
