package ru.nsu.fit.oop.melnikov.game.snake.model;

import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.FieldGenerator;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.SnakePoint;

import java.util.ArrayList;
import java.util.List;

public class GameGenerator {

    private GameGenerator() {}

    public static GameData generate(int fieldWidth, int fieldHeight, int wallCovPct) {
        Field field = FieldGenerator.generate(fieldWidth, fieldHeight, wallCovPct);
        Snake snake = null;
        for (Cell[] cells : field.getCells()) {
            for (Cell cell : cells) {
                if (!cell.contains(Wall.class)) {
                    snake = new Snake(field, new ArrayList<>(List.of(new SnakePoint(cell))));
                    break;
                }
            }
            if (snake != null) break;
        }
        return new GameData(field, new ArrayList<>(List.of(snake)));
    }

}
