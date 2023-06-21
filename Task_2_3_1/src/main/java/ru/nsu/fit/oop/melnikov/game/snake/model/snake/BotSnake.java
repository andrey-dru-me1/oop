package ru.nsu.fit.oop.melnikov.game.snake.model.snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
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
    Direction res = this.getDirection();
    Collections.shuffle(directions);
    for (Direction direction : directions) {
      if (direction.isOpposite(this.getDirection())) continue;

      Cell cell = field.getCell(field.calculateNextPoint(body.getHeadPoint(), direction));
      if (!cell.contains(Wall.class) && !cell.contains(SnakeNode.class)) {
        res = direction;
        break;
      }
    }
    super.setDirection(res);
    super.move();
  }
}
