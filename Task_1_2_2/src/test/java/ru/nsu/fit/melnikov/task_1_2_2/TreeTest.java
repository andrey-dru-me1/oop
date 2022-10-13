package ru.nsu.fit.melnikov.task_1_2_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TreeTest {

    @Test
    void test() {
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

}