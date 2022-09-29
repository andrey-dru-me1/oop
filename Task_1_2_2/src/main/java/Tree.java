import java.util.Arrays;

public class Tree<T> {

    Node<T> root;

    public Tree() {
        root = new Node<>(null);
    }

    public static class Node<T> {

        T value;
        Node<T>[] children;

        @SuppressWarnings("unchecked")
        public Node(T value) {
            this.value = value;
            children = new Tree.Node[0];
        }

        public Node<T> add(T value) {
            children = Arrays.copyOf(children, children.length + 1);
            children[children.length - 1] = new Node<>(value);
            return children[children.length - 1];
        }

    }

    public Node<T> add(T value) {
        return root.add(value);
    }

    public Node<T> add(Node<T> node, T value) {
        return node.add(value);
    }

}
