package ru.nsu.fit.melnikov.oop.task_1_2_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

class GraphTest {

    void test(Graph<Double, Double> graph) {
        graph.addVertex(7.5);
        graph.addVertex(8.99);
        Assertions.assertTrue(graph.addEdge(7.5, 8.99, 5.0, 5.0));
        Assertions.assertEquals(
                graph.addVertices(new Double[]{4.55, 4.15, 6.3, 8.19, 0.00006}),
                Arrays.asList(true, true, true, true, true)
        );

        graph.addEdge(8.99, 7.5, 1.0, 1.0);

        Assertions.assertEquals(
                graph.setEdgeWeight(5.0, 8.95),
                5.0
        );
        graph.setEdgeIncidents(5.0, 8.99, 4.15);
        graph.setVertexValue(8.99, 9.88);

        graph.addEdge(7.5, 9.88, 8.6, 8.6);
        graph.removeVertex(7.5);

        graph.addEdge(6.3, 0.00006, 6.98, 6.98);

        Assertions.assertEquals(
                graph.getEdgeWeight(graph.getEdgesByVertices(6.3, 0.00006).get(0)),
                6.98
        );
        Assertions.assertEquals(graph.getEdgeWeight(6.98), 6.98);
        Assertions.assertEquals(graph.getEdgeFrom(6.98), 6.3);
        Assertions.assertEquals(graph.getEdgeTo(6.98), 0.00006);

        graph.setEdgeValue(6.98, 8.98);

        Assertions.assertEquals(graph.getVerticesCount(), 6);
        Assertions.assertEquals(graph.getEdgesCount(), 2);
    }

    @Test
    void tests() {

        GraphAdjacencyList<Double, Double> graph = new GraphAdjacencyList<>();
        test(graph);

        GraphIncidentMatrix<Double, Double> graph1 = new GraphIncidentMatrix<>();
        test(graph1);

        GraphAdjacencyMatrix<Double, Double> graph2 = new GraphAdjacencyMatrix<>();
        test(graph2);

    }

    @Test
    void testSort() {

        Graph<Double, Double> g = new GraphAdjacencyList<>();
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

        Assertions.assertTrue(g.containsEdge(9.));

    }

    @Test
    void fromFiles() throws FileNotFoundException {

        FileReader file = new FileReader(
                "src/test/java/ru/nsu/fit/melnikov/oop/task_1_2_3/adjacency_matrix.txt"
        );

        Graph<String, Integer> g = GraphParser.toAdjacencyMatrix(file);

        Assertions.assertEquals(g.getEdgesCount(), 6);
        Assertions.assertEquals(g.getVerticesCount(), 5);

        Assertions.assertEquals(g.sort("A"), Arrays.asList("A", "B", "E", "C", "D"));

        Assertions.assertTrue(g.containsVertex("E"));

        //-----------------------------------------------------------------------------------------

        file = new FileReader(
                "src/test/java/ru/nsu/fit/melnikov/oop/task_1_2_3/adjacency_list.txt"
        );

        g = GraphParser.toAdjacencyList(file);

        Assertions.assertEquals(g.getEdgesCount(), 6);
        Assertions.assertEquals(g.getVerticesCount(), 5);

        //-----------------------------------------------------------------------------------------

        file = new FileReader(
                "src/test/java/ru/nsu/fit/melnikov/oop/task_1_2_3/incident_matrix.txt"
        );

        g = GraphParser.toIncidentMatrix(file);

        Assertions.assertEquals(g.getEdgesCount(), 6);
        Assertions.assertEquals(g.getVerticesCount(), 5);

    }

}