import java.util.Arrays;

public class Tree<T> {

    private final Node<T> root;

    public Tree() {
        root = new Node<>(null, null);
    }

    public static class Node<T> {

        T value;
        Node<T>[] children;
        Node<T> parent;

        @SuppressWarnings("unchecked")
        public Node(Node<T> parent, T value) {
            this.value = value;
            children = new Node[0];
            this.parent = parent;
        }

        public Node<T> add(T value) {
            children = Arrays.copyOf(children, children.length + 1);
            children[children.length - 1] = new Node<>(this, value);
            return children[children.length - 1];
        }

        public void remove() {
            for (Node<T> i : children) {
                i.remove();
            }

            if (parent == null) return;

            for (int i = 0; i < parent.children.length; i++) {
                if (parent.children[i] == this) {
                    System.arraycopy(parent.children, i + 1, parent.children, i, parent.children.length - i - 1);
                    parent.children = Arrays.copyOf(parent.children, parent.children.length - 1);
                }
            }

        }

        public String toString() {
            StringBuilder res = new StringBuilder("( " + value + ", [");
            for (int i = 0; i < children.length; i++) {

                res.append(children[i].toString());

                if (i < children.length - 1) {
                    res.append(", ");
                }
            }
            res.append("] )");
            return res.toString();
        }

    }

    public Node<T> add(T value) {
        return root.add(value);
    }

    public Node<T> add(Node<T> node, T value) {
        return node.add(value);
    }

    public void remove() {
        root.remove();
    }

    public void remove(Node<T> node) {
        node.remove();
    }

    public String toString() {
        return root.toString();
    }

}
