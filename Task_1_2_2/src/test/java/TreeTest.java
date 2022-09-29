import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreeTest {

    @Test
    void test() {
        Tree<Integer> tree = new Tree<>();
        Tree.Node<Integer> seven = tree.add(7);
        Tree.Node<Integer> eight = tree.add(seven, 8);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 8, [] )] )] )");

        seven.add(9);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 8, [] ), ( 9, [] )] )] )");

        tree.remove(eight);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 7, [( 9, [] )] )] )");

        tree.depthIterate(val -> val * 2);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 14, [( 18, [] )] )] )");

        tree.breadthIterate(x -> x / 3);
        Assertions.assertEquals(tree.toString().trim(), "( null, [( 4, [( 6, [] )] )] )");
    }

}