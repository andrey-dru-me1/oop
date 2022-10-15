package ru.nsu.fit.melnikov.task_1_2_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class TreeTest {

    @Test
    void testInts() {
        Tree<Integer> tree = new Tree<>();
        tree.add(7);
        tree.add(7, 8);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 8, [] )] )] )");

        tree.addById(tree.getId(7), 9);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 8, [] ), ( 9, [] )] )] )");

        tree.set(8, tree.get(8) / 2);

        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 4, [] ), ( 9, [] )] )] )");
        Assertions.assertEquals(tree.toList().toString().trim(), "[7, 4, 9]");
        Assertions.assertEquals(Arrays.toString(tree.toArray()), "[7, 4, 9]");

        tree.remove(4);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 9, [] )] )] )");

        tree.removeById(tree.getId(7));
        Assertions.assertEquals(tree.toString().trim(), "( null, [] )");

    }

    @Test
    void testDoubles() {

        Tree<Double> tree = new Tree<>();
        Assertions.assertTrue(tree.isEmpty());

        tree.add(6.79);
        Assertions.assertTrue(tree.contains(6.79));

        int sndChild = tree.add2(9.87);
        int trdChild = tree.addById(sndChild, 8.957);
        tree.addById(tree.getId(9.87), -159.896);
        tree.addById(trdChild, 0.0069);

        Iterator<Double> iter = tree.iteratorDFS();
        List<Double> helper = new ArrayList<>();
        while (iter.hasNext()) {
            helper.add(iter.next());
        }
        Assertions.assertEquals(helper, Arrays.asList(6.79, 9.87, 8.957, 0.0069, -159.896));

        Assertions.assertArrayEquals(tree.toArray(), new Double[]{6.79, 9.87, 8.957, -159.896, 0.0069});

        tree.addAll(Arrays.asList(7.6, 7.6, 9.9999));
        Assertions.assertArrayEquals(
                tree.toArray(),
                new Double[]{6.79, 9.87, 7.6, 7.6, 9.9999, 8.957, -159.896, 0.0069}
        );

        tree.retainAll(Arrays.asList(7.6, -159.896, 9.87, 9.9999, 0.0069));

        Double[] toTest = new Double[3];
        toTest = tree.toArray(toTest);
        Assertions.assertArrayEquals(toTest, new Double[]{9.87, 7.6, 7.6, 9.9999, -159.896});

        toTest = new Double[tree.size() + 3];
        tree.toArray(toTest);
        Assertions.assertArrayEquals(toTest, new Double[]{9.87, 7.6, 7.6, 9.9999, -159.896, null, null, null});

        tree.removeAll(Arrays.asList(7.6, -159.896));
        Assertions.assertArrayEquals(tree.toArray(), new Double[]{9.87, 9.9999});

        Assertions.assertFalse(tree.containsAll(Arrays.asList(9.87, -159.896)));
        Assertions.assertTrue(tree.containsAll(Arrays.asList(9.87, 9.9999)));

        tree.clear();
        Assertions.assertNull(tree.toString());

    }

}