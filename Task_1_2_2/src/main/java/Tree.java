import java.util.Arrays;
import java.util.function.Function;

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

        public void depthIterate(Function<T, T> foo) {
            for (Node<T> i : children) {
                i.depthIterate(foo);
            }
            if (value != null) value = foo.apply(value);
        }

        private void breadthIterate(Node<T>[] queue, Function<T, T> foo) {

            while (queue.length > 0) {
                int prevLen = queue.length;
                queue = Arrays.copyOf(queue, queue.length + queue[0].children.length);
                System.arraycopy(queue[0].children, 0, queue, prevLen, queue[0].children.length);

                if (queue[0].value != null) {
                    queue[0].value = foo.apply(queue[0].value);
                }

                System.arraycopy(queue, 1, queue, 0, queue.length - 1);
                queue = Arrays.copyOf(queue, queue.length - 1);
            }

        }

        @SuppressWarnings("unchecked")
        public void breadthIterate(Function<T, T> foo) {
            breadthIterate(new Node[]{this}, foo);
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

    public void depthIterate(Function<T, T> foo) {
        root.depthIterate(foo);
    }

    public void depthIterate(Node<T> node, Function<T, T> foo) {
        node.depthIterate(foo);
    }

    public void breadthIterate(Function<T, T> foo) {
        root.breadthIterate(foo);
    }

    public void breadthIterate(Node<T> node, Function<T, T> foo) {
        node.breadthIterate(foo);
    }

    public String toString() {
        return root.toString();
    }

}
