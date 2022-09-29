import org.junit.jupiter.api.Test;

class TreeTest {

    @Test
    void test() {
        Tree<Integer> tree = new Tree<>();
        Tree.Node<Integer> seven = tree.add(7);
        tree.add(seven, 8);
        seven.add(9);
    }

}