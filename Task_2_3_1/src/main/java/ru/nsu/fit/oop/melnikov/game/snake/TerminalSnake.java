package ru.nsu.fit.oop.melnikov.game.snake;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import ru.nsu.fit.oop.melnikov.game.data.loader.DataLoader;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Apple;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.SnakeNode;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;

public class TerminalSnake {

  public static void main(String[] args) throws IOException {
    DataLoader dataLoader = new DataLoader("big_map.txt");

    Snake snake = dataLoader.getSnake();
    Field field = dataLoader.getField();
    field.generateApple();

    DefaultTerminalFactory defaultTerminalFactory =
        new DefaultTerminalFactory()
            .setInitialTerminalSize(new TerminalSize(field.getWidth() * 2, field.getHeight()));

    try (Screen screen = defaultTerminalFactory.createScreen();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor()) {

      screen.startScreen();

      redraw(screen, field);

      executorService.scheduleAtFixedRate(
          () -> {
            try {

              KeyStroke keyStroke = screen.pollInput();

              Direction direction = snake.getDirection();
              if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                  case ArrowUp -> direction = Direction.UP;
                  case ArrowDown -> direction = Direction.DOWN;
                  case ArrowLeft -> direction = Direction.LEFT;
                  case ArrowRight -> direction = Direction.RIGHT;
                }
              }

              snake.move(direction);

              if (snake.isDestroyed()) {
                executorService.shutdown();
              }

              redraw(screen, field);

            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          },
          100,
          100,
          TimeUnit.MILLISECONDS);

      executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static void redraw(Screen screen, Field field) throws IOException {
    for (Cell[] row : field.getCells()) {
      for (Cell cell : row) {
        char cellChar;
        if (cell.contains(Wall.class)) {
          cellChar = '#';
        } else if (cell.contains(SnakeNode.class)) {
          cellChar = 'o';
        } else if (cell.contains(Apple.class)) {
          cellChar = 'a';
        } else {
          cellChar = ' ';
        }
        TextCharacter textCharacter = new TextCharacter(cellChar);
        screen.setCharacter(cell.getX() * 2, cell.getY(), textCharacter);
      }
    }
    screen.refresh();
  }
}
