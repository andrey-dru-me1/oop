package ru.nsu.fit.melnikov.oop.task_1_2_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GraphIncMtrxTest {

    @Test
    void test() {

        GraphIncMtrx<Double, Double> graph = new GraphIncMtrx<>();

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
        Assertions.assertEquals(graph.ECnt(), 1);

    }

}