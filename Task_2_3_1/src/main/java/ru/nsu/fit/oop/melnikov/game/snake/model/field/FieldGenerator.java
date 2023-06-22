package ru.nsu.fit.oop.melnikov.game.snake.model.field;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javafx.util.Pair;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;

public class FieldGenerator {
  public static Field generate(int width, int height, int wallCovPct) {
    GeneratingCell[][] genField = new GeneratingCell[width][height];

    for (GeneratingCell[] generatingCells : genField) {
      for (GeneratingCell generatingCell : generatingCells) {
        if (ThreadLocalRandom.current().nextInt(100) < wallCovPct) {
          generatingCell = GeneratingCell.WALL;
        } else {
          generatingCell = GeneratingCell.EMPTY;
        }
      }
    }

    List<List<Point>> cliques = new ArrayList<>();
    Collection<Point> markedCells = new ArrayList<>();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (genField[i][j] == GeneratingCell.EMPTY && !markedCells.contains(new Point(i, j))) {
          List<Point> clique = new ArrayList<>();
          Queue<Point> grayCells = new ArrayDeque<>();
          grayCells.add(new Point(i, j));

          Collection<Point> children =
              List.of(
                  new Point((i + 1) % width, j),
                  new Point((i + width - 1) % width, j),
                  new Point(i, (j + 1) % height),
                  new Point(i, (j + height - 1) % height));

          while (!grayCells.isEmpty()) {
            Point grayCellCoords = grayCells.poll();

            for (Point child : children) {
              GeneratingCell nextCell = genField[child.x()][child.y()];
              if (nextCell == GeneratingCell.EMPTY
                  && !markedCells.contains(child)
                  && !grayCells.contains(child)) {
                grayCells.add(child);
              }
            }

            markedCells.add(grayCellCoords);
          }

          cliques.add(clique);
        }
      }
    }

    if(cliques.size() > 1) {
      for(int i = 0; i < cliques.size() - 1; i++) {
        Point point1 = cliques.get(i).get(0);
        Point point2 = cliques.get(i + 1).get(0);

        for(int j = Math.min(point1.x, point2.x); j < Math.max(point1.x, point2.x); j++) {
          genField[j][point1.y] = GeneratingCell.EMPTY;
        }
        for(int j = Math.min(point1.y, point2.y); j < Math.max(point1.y, point2.y); j++) {
          genField[point2.x][j] = GeneratingCell.EMPTY;
        }
      }
    }

    Cell[][] cells = new Cell[width][height];
    for(int i = 0; i < width; i++) {
      for(int j = 0; j < height; j++) {
        cells[i][j] = new Cell(i, j);
        if(genField[i][j] == GeneratingCell.WALL)
          cells[i][j].add(new Wall());
      }
    }

    return new Field(cells);

  }

  private record Point(Integer x, Integer y) {}

  private enum GeneratingCell {
    WALL,
    EMPTY
  }
}
