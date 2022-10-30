package ru.nsu.fit.melnikov.oop.task_1_2_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class GraphTest {

    void test(AbstractGraph<Double, Double> graph) {
        graph.addVertex(7.5);
        graph.addVertex(8.99);
        graph.addEdge(7.5, 8.99, 5.0, 5.0);
        graph.addVertices(new Double[]{4.55, 4.15, 6.3, 8.19, 0.00006});

        graph.addEdge(8.99, 7.5, 1.0, 1.0);

        graph.setEdgeWeight(5.0, 8.95);
        graph.setEdgeIncidents(5.0, 8.99, 4.15);
        graph.setVertexValue(8.99, 9.88);

        graph.addEdge(7.5, 9.88, 8.6, 8.6);
        graph.removeVertex(7.5);

        graph.addEdge(6.3, 0.00006, 6.98, 6.98);

        Assertions.assertEquals(graph.getEdgeWeight(graph.getEdgesByVertices(6.3, 0.00006).get(0)), 6.98);
        Assertions.assertEquals(graph.getEdgeWeight(6.98), 6.98);
        Assertions.assertEquals(graph.getEdgeFrom(6.98), 6.3);
        Assertions.assertEquals(graph.getEdgeTo(6.98), 0.00006);

        Assertions.assertEquals(graph.getVerticesCount(), 6);
        Assertions.assertEquals(graph.getEdgesCount(), 2);
    }

    @Test
    void tests() {

        GraphAdjList<Double, Double> graph = new GraphAdjList<>();
        test(graph);

        GraphIncMtrx<Double, Double> graph1 = new GraphIncMtrx<>();
        test(graph1);

        GraphAdjMtrx<Double, Double> graph2 = new GraphAdjMtrx<>();
        test(graph2);

    }

    @Test
    void testSort() {

        AbstractGraph<Double, Double> g = new GraphAdjList<>();
        g.addVertices(new Double[]{1., 2., 3., 4., 5., 6., 7., 8., 9.});
        g.addEdge(1., 2., 1., 1.);
        g.addEdge(1., 3., 7., 2.);
        g.addEdge(2., 3., 3., 3.);
        g.addEdge(3., 4., 1., 4.);
        g.addEdge(1., 5., 3., 5.);
        g.addEdge(4., 5., 1., 6.);
        Assertions.assertEquals(g.sort(1.), Arrays.asList(1., 2., 5., 3., 4.));

        g.addEdge(6., 7., -1., 7.);
        g.addEdge(7., 8., -1., 8.);
        g.addEdge(8., 6., -1., 9.);
        Assertions.assertThrowsExactly(NegativeLoopException.class, () -> g.sort(6.));

    }

    @Test
    void fromFile() throws FileNotFoundException {

        AbstractGraph<String, Integer> g = new GraphAdjMtrx<>();

        FileReader file = new FileReader(
                "src/test/java/ru/nsu/fit/melnikov/oop/task_1_2_3/input_matrix.txt"
        );
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        List<String> verts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            verts.add(scanner.next());
            g.addVertex(verts.get(i));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String weight = scanner.next();
                if (!weight.equals("-")) {
                    g.addEdge(verts.get(i), verts.get(j), Double.parseDouble(weight), i * n + j);
                }
            }
        }

        Assertions.assertEquals(g.getEdgesCount(), 6);
        Assertions.assertEquals(g.getVerticesCount(), 5);

        Assertions.assertEquals(g.sort("A"), Arrays.asList("A", "B", "E", "C", "D"));

    }

}