import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

/**
 * Represents a tree type. Has only root and its descendants.
 *
 * @param <T> Type of elements' values
 */
public class Tree<T> implements Collection {

    private final Node<T> root;
    private int size;

    public Tree() {
        root = new Node<>(null, null);
        size = 0;
    }

    /**
     * Represents a tree's nodes with their values and lists of sons.
     *
     * @param <T> Type of elements' values
     */
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

        /**
         * Adds new child to current node with the specified value.
         *
         * @param value The value that will be bound on the new element.
         * @return The new node.
         */
        public Node<T> add(T value) {
            children = Arrays.copyOf(children, children.length + 1);
            children[children.length - 1] = new Node<>(this, value);
            return children[children.length - 1];
        }

        /**
         * Removes current child with its descendants.
         */
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

        /**
         * Applies the specified function to each descendant's value of current node
         * and to the current node value itself with depth-search.
         *
         * @param foo Function that will be applied to each descendant's value of current node
         *            and to the current node value itself.
         */
        public void depthIterate(Function<T, T> foo) {
            for (Node<T> i : children) {
                i.depthIterate(foo);
            }
            if (value != null) value = foo.apply(value);
        }

        /**
         * Helper method that use queue. Doesn't visible outside the Node class.
         *
         * @param queue Queue where all descendant nodes will be contained.
         * @param foo   Function that will be applied to each descendant's value of current node
         *              and to the current node value itself.
         */
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

        /**
         * Applies the specified function to each descendant's value of current node
         * and to the current node value itself with breadth-search.
         *
         * @param foo Function that will be applied to each descendant's value of current node
         *            and to the current node value itself.
         */
        @SuppressWarnings("unchecked")
        public void breadthIterate(Function<T, T> foo) {
            breadthIterate(new Node[]{this}, foo);
        }

        /**
         * Convert tree to a string.
         *
         * @return The String-representation of the tree.
         */
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
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
