package ru.nsu.fit.melnikov.oop.task_1_2_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GraphTest {

    @Test
    void test() {

        Graph<Double> graph = new Graph<>();

        int fstV = graph.addVertex(7.5);
        int sndV = graph.addVertex(8.99);
        int fstE = graph.addEdge(fstV, sndV, 5.0);
        graph.addVertices(new Double[]{4.55, 4.55, 6.3, 8.99, 0.00006});

        graph.addEdge(graph.getVerticesWithValue(8.99).get(0), fstV, 1.0);

        graph.setEdgeWeight(fstE, 8.95);
        graph.setEdgeInceds(fstE, sndV, graph.getVerticesWithValue(4.55).get(1));
        graph.setVertexValue(graph.getVerticesWithValue(8.99).get(0), 9.88);
        Assertions.assertEquals(graph.getVertexValue(sndV), 9.88);

        graph.addEdge(fstV, sndV, 8.6);
        graph.removeVertex(fstV);

        graph.addEdge((fstV = graph.getVerticesWithValue(6.3).get(0)), (sndV = graph.getVerticesWithValue(0.00006).get(0)), 6.98);
        graph.removeEdge(fstE);

        Assertions.assertEquals(graph.getEdgeWeight(fstE = graph.getEdgesByVerts(fstV, sndV).get(0)), 6.98);
        Assertions.assertEquals(graph.getEdgeWeight(fstE), 6.98);
        Assertions.assertEquals(graph.getEdgeFrom(fstE), fstV);
        Assertions.assertEquals(graph.getEdgeTo(fstE), sndV);

        Assertions.assertEquals(graph.VerticesCount(), 6);
        Assertions.assertEquals(graph.EdgesCount(), 1);

    }

}