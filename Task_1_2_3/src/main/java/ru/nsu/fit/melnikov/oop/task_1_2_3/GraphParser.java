package ru.nsu.fit.melnikov.oop.task_1_2_3;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphParser {

    static GraphAdjacencyMatrix<String, Integer> toAdjacencyMatrix(FileReader file) {

        GraphAdjacencyMatrix<String, Integer> g = new GraphAdjacencyMatrix<>();

        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        List<String> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(scanner.next());
            g.addVertex(vertices.get(i));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String weight = scanner.next();
                if (!weight.equals("-")) {
                    g.addEdge(
                            vertices.get(i),
                            vertices.get(j),
                            Double.parseDouble(weight),
                            i * n + j
                    );
                }
            }
        }

        return g;
    }

    static GraphAdjacencyList<String, Integer> toAdjacencyList(FileReader file) {

        GraphAdjacencyList<String, Integer> g = new GraphAdjacencyList<>();

        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        List<String> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(scanner.next());
            g.addVertex(vertices.get(i));
        }
        int id = 0;
        for (int i = 0; i < n; i++) {
            String adjacencyVertex;
            while (!(adjacencyVertex = scanner.next()).equals("/")) {
                g.addEdge(
                        vertices.get(i),
                        adjacencyVertex,
                        1.,
                        id++
                );
            }
        }

        return g;
    }

    static GraphIncidentMatrix<String, Integer> toIncidentMatrix(FileReader file) {

        GraphIncidentMatrix<String, Integer> g = new GraphIncidentMatrix<>();

        Scanner scanner = new Scanner(file);
        int verticesCount = scanner.nextInt();
        int edgesCount = scanner.nextInt();
        List<String> vertices = new ArrayList<>();
        for (int i = 0; i < verticesCount; i++) {
            vertices.add(scanner.next());
            g.addVertex(vertices.get(i));
        }
        List<Integer> edges = new ArrayList<>();
        for (int i = 0; i < edgesCount; i++) {
            edges.add(scanner.nextInt());
        }
        for (int i = 0; i < edgesCount; i++) {
            String vertexFrom = null;
            String vertexTo = null;
            for (int j = 0; j < verticesCount; j++) {
                String isEdge = scanner.next();
                switch (isEdge) {
                    case "-":
                        break;
                    case "1":
                        vertexFrom = vertices.get(j);
                        break;
                    case "0":
                        vertexTo = vertices.get(j);
                        break;
                }
            }
            g.addEdge(vertexFrom, vertexTo, 1., edges.get(i));
        }

        return g;
    }

}
