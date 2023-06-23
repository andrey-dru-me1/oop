package ru.nsu.fit.oop.melnikov.game.snake.model.field;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.Cell;
import ru.nsu.fit.oop.melnikov.game.snake.model.field.cell.objects.Wall;

public class FieldGenerator {
  private final int width;
  private final int height;
  private final GeneratingCell[][] genField;

  public FieldGenerator(int width, int height, int wallCovPct) {
    this.width = width;
    this.height = height;
    this.genField = randomizeField(wallCovPct);
  }

  public static Field generate(int width, int height, int wallCovPct) {
    FieldGenerator fieldGen = new FieldGenerator(width, height, wallCovPct);

    List<List<Point>> cliques = fieldGen.findCliques();

    fieldGen.connectAllCliques(cliques);

    return fieldGen.genField2Field();
  }

  private Field genField2Field() {
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

  private void connectAllCliques(List<List<Point>> cliques) {
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
  }

  private List<List<Point>> findCliques() {
    List<List<Point>> cliques = new ArrayList<>();
    Collection<Point> markedCells = new ArrayList<>();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Point currentPoint = new Point(i, j);
        if (genField[i][j] == GeneratingCell.EMPTY && !markedCells.contains(currentPoint)) {
          cliques.add(markEntireClique(currentPoint, markedCells));
        }
      }
    }
    return cliques;
  }

  private List<Point> markEntireClique(Point startPoint, Collection<Point> markedCells) {
    List<Point> clique = new ArrayList<>();
    Queue<Point> grayCells = new ArrayDeque<>();
    grayCells.add(startPoint);

    int i = startPoint.x();
    int j = startPoint.y();
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
      clique.add(grayCellCoords);
    }

    return clique;
  }

  private GeneratingCell[][] randomizeField(int wallCovPct) {
    GeneratingCell[][] genFieldCreator = new GeneratingCell[width][height];

    for(int i = 0; i < width; i++) {
      for(int j = 0; j < height; j++) {
        if(ThreadLocalRandom.current().nextInt(100) < wallCovPct) {
          genFieldCreator[i][j] = GeneratingCell.WALL;
        } else {
          genFieldCreator[i][j] = GeneratingCell.EMPTY;
        }
      }
    }
    return genFieldCreator;
  }

  private record Point(Integer x, Integer y) {}

  private enum GeneratingCell {
    WALL,
    EMPTY
  }
}
