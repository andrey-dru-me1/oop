package ru.nsu.fit.oop.melnikov.game.snake.terminal;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import ru.nsu.fit.oop.melnikov.game.data.loader.DataLoader;
import ru.nsu.fit.oop.melnikov.game.snake.model.GameData;
import ru.nsu.fit.oop.melnikov.game.snake.model.direction.Direction;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.Field;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.CellObject;
import ru.nsu.fit.oop.melnikov.game.snake.model.snake.Snake;
import ru.nsu.fit.oop.melnikov.game.snake.terminal.view.CellObjectViewsRepository;

public class TerminalSnake {

  public static void main(String[] args) throws IOException {
    GameData gameData = DataLoader.load("big_map.txt");

    List<Snake> snakes = gameData.snakes();
    Field field = gameData.field();
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

              Snake playerSnake = snakes.get(0);

              KeyStroke keyStroke = screen.pollInput();

              Direction direction = playerSnake.getDirection();
              if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                  case ArrowUp -> direction = Direction.UP;
                  case ArrowDown -> direction = Direction.DOWN;
                  case ArrowLeft -> direction = Direction.LEFT;
                  case ArrowRight -> direction = Direction.RIGHT;
                  default -> {
                    // Do nothing on any other button
                  }
                }
              }

              playerSnake.move(direction);

              if (playerSnake.isDestroyed()) {
                executorService.shutdown();
              }

              for(int i = 1; i < snakes.size(); i++) {
                snakes.get(i).move();
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

  /**
   * Draw the whole field.
   *
   * @param screen Screen where to draw
   * @param field what to draw
   * @throws IOException see {@link Screen#refresh()}
   */
  private static void redraw(Screen screen, Field field) throws IOException {
    for (Cell[] row : field.getCells()) {
      for (Cell cell : row) {

        List<CellObject> cellObjects = cell.getCellObjects().stream().toList();
        CellObject cellObject = cellObjects.get(cellObjects.size() - 1);

        char cellChar;
        cellChar =
            CellObjectViewsRepository.INSTANCE.getCellObjectView(cellObject.getClass()).getSymbol();

        TextCharacter textCharacter = new TextCharacter(cellChar);
        screen.setCharacter(cell.getX() * 2, cell.getY(), textCharacter);
      }
    }
    screen.refresh();
  }
}