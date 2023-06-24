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

    fieldGen.removeSquares();

    List<List<Point>> cliques = fieldGen.findCliques();
    while (cliques.size() > 1) {
      cliques.sort(Comparator.comparingInt(List::size));
      fieldGen.breakWalls(cliques.get(0));
      cliques = fieldGen.findCliques();
    }

    return fieldGen.genField2Field();
  }

  private void breakWalls(List<Point> clique) {
    Point point = clique.get(ThreadLocalRandom.current().nextInt(clique.size()));
    Point beginPoint = new Point(point.x, point.y);

    Point[] directions =
        new Point[] {new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)};
    Point direction = directions[ThreadLocalRandom.current().nextInt(4)];


    point = new Point((point.x + width + direction.x) % width, (point.y + height + direction.y) % height);
    genField[point.x][point.y] = GeneratingCell.EMPTY;
    while (!hasEmptyNeighbour(clique, point) && point != beginPoint) {
      point = new Point((point.x + width + direction.x) % width, (point.y + height + direction.y) % height);
      genField[point.x][point.y] = GeneratingCell.EMPTY;
    }
  }

  private boolean hasEmptyNeighbour(List<Point> clique, Point point) {
    for (int i = -1; i <= 1; i += 2) {
      int x = (point.x + width + i) % width;
      if (genField[x][point.y] == GeneratingCell.EMPTY && !clique.contains(new Point(x, point.y)))
        return true;

      int y = (point.y + height + i) % height;
      if (genField[point.x][y] == GeneratingCell.EMPTY && !clique.contains(new Point(point.x, y)))
        return true;
    }
    return false;
  }

  private Field genField2Field() {
    Cell[][] cells = new Cell[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        cells[i][j] = new Cell(i, j);
        if (genField[i][j] == GeneratingCell.WALL) cells[i][j].add(new Wall());
      }
    }

    return new Field(cells);
  }

  private void removeSquares() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {

        int[][] options = new int[][] {{i, (i + 1) % width}, {j, (j + 1) % height}};
        int wallsCount = countWallsInSquare(options);

        if (wallsCount == 4) {
          int k = ThreadLocalRandom.current().nextInt(2);
          int l = ThreadLocalRandom.current().nextInt(2);
          genField[options[0][k]][options[1][l]] = GeneratingCell.EMPTY;
        }
      }
    }
  }

  private int countWallsInSquare(int[][] square) {
    int wallsCount = 0;
    for (int k = 0; k < 2; k++) {
      for (int l = 0; l < 2; l++) {
        if (genField[square[0][k]][square[1][l]] == GeneratingCell.WALL) wallsCount++;
      }
    }
    return wallsCount;
  }

  private List<List<Point>> findCliques() {
    List<List<Point>> cliques = new ArrayList<>();
    Collection<Point> markedCells = new HashSet<>();
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

    while (!grayCells.isEmpty()) {
      Point grayCellCoords = grayCells.poll();

      int i = grayCellCoords.x();
      int j = grayCellCoords.y();
      Collection<Point> children =
              List.of(
                      new Point((i + 1) % width, j),
                      new Point((i + width - 1) % width, j),
                      new Point(i, (j + 1) % height),
                      new Point(i, (j + height - 1) % height));

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

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (ThreadLocalRandom.current().nextInt(100) < wallCovPct) {
          genFieldCreator[i][j] = GeneratingCell.WALL;
        } else {
          genFieldCreator[i][j] = GeneratingCell.EMPTY;
        }
      }
    }
    return genFieldCreator;
  }

  private enum GeneratingCell {
    WALL,
    EMPTY
  }

  private record Point(Integer x, Integer y) {}
}
