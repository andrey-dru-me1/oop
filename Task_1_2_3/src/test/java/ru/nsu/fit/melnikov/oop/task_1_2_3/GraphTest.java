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
        graph.addV(7.5);
        graph.addV(8.99);
        graph.addE(7.5, 8.99, 5.0, 5.0);
        graph.addVerts(new Double[]{4.55, 4.15, 6.3, 8.19, 0.00006});

        graph.addE(8.99, 7.5, 1.0, 1.0);

        graph.setEdgeW(5.0, 8.95);
        graph.setEInceds(5.0, 8.99, 4.15);
        graph.setVVal(8.99, 9.88);

        graph.addE(7.5, 9.88, 8.6, 8.6);
        graph.removeV(7.5);

        graph.addE(6.3, 0.00006, 6.98, 6.98);

        Assertions.assertEquals(graph.getEdgeW(graph.getEdgesByVerts(6.3, 0.00006).get(0)), 6.98);
        Assertions.assertEquals(graph.getEdgeW(6.98), 6.98);
        Assertions.assertEquals(graph.getEFrom(6.98), 6.3);
        Assertions.assertEquals(graph.getETo(6.98), 0.00006);

        Assertions.assertEquals(graph.VCnt(), 6);
        Assertions.assertEquals(graph.ECnt(), 2);
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
        g.addVerts(new Double[]{1., 2., 3., 4., 5., 6., 7., 8., 9.});
        g.addE(1., 2., 1., 1.);
        g.addE(1., 3., 7., 2.);
        g.addE(2., 3., 3., 3.);
        g.addE(3., 4., 1., 4.);
        g.addE(1., 5., 3., 5.);
        g.addE(4., 5., 1., 6.);
        Assertions.assertEquals(g.sort(1.), Arrays.asList(1., 2., 5., 3., 4.));

        g.addE(6., 7., -1., 7.);
        g.addE(7., 8., -1., 8.);
        g.addE(8., 6., -1., 9.);
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
            g.addV(verts.get(i));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String weight = scanner.next();
                if (!weight.equals("-")) {
                    g.addE(verts.get(i), verts.get(j), Double.parseDouble(weight), i * n + j);
                }
            }
        }

        Assertions.assertEquals(g.ECnt(), 6);
        Assertions.assertEquals(g.VCnt(), 5);

        Assertions.assertEquals(g.sort("A"), Arrays.asList("A", "B", "E", "C", "D"));

    }

}