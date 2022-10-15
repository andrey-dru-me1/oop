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
        Tree<Integer>.Node seven = tree.nAdd(7);
        Tree<Integer>.Node eight = tree.nAdd(seven, 8);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 8, [] )] )] )");

        seven.add(9);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 8, [] ), ( 9, [] )] )] )");

        eight.set(eight.get() / 2);

        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 4, [] ), ( 9, [] )] )] )");
        Assertions.assertEquals(tree.toList().toString().trim(), "[7, 4, 9]");
        Assertions.assertEquals(Arrays.toString(tree.toArray()), "[7, 4, 9]");

        tree.remove(eight);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 9, [] )] )] )");

        seven.remove();
        Assertions.assertEquals(tree.toString().trim(), "( null, [] )");

    }

    @Test
    void testDoubles() {

        Tree<Double> tree = new Tree<>();
        Assertions.assertTrue(tree.isEmpty());

        tree.add(6.79);
        Assertions.assertTrue(tree.contains(6.79));

        Tree<Double>.Node sndChild = tree.nAdd(9.87);
        Tree<Double>.Node trdChild = sndChild.add(8.957);
        tree.add(sndChild, -159.896);
        tree.add(trdChild, 0.0069);

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

        Assertions.assertNull(trdChild.toString());

        Assertions.assertFalse(tree.containsAll(Arrays.asList(9.87, -159.896)));
        Assertions.assertTrue(tree.containsAll(Arrays.asList(9.87, 9.9999)));

        tree.clear();
        Assertions.assertNull(tree.toString());

    }

}