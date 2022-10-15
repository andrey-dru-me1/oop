package ru.nsu.fit.melnikov.task_1_2_2;

import java.util.*;

/**
 * Represents a tree type. Has only ROOT and its descendants.
 *
 * @param <T> Type of elements' values
 */
public class Tree<T> implements Collection<T> {

    private final Node ROOT;
    private int size;

    public Tree() {
        ROOT = new Node(null, null);
        size = 0;
    }

    /**
     * Represents a tree's nodes with their values and lists of sons.
     */
    public class Node {

        Object value;
        List<Node> children;
        Node parent;

        public Node(Node parent, T value) {
            this.value = value;
            children = new ArrayList<>();
            this.parent = parent;
        }

        public void set(T value) {
            this.value = value;
        }

        @SuppressWarnings("unchecked")
        public T get() {
            return (T) value;
        }

        /**
         * Adds new child to current node with the specified value.
         *
         * @param value The value that will be bound on the new element.
         * @return The new node.
         */
        public Node add(T value) {
            Node newNode = new Node(this, value);
            children.add(newNode);
            Tree.this.size++;
            return newNode;
        }

        /**
         * Removes current child with its descendants.
         */
        public boolean remove() {
            while (children.size() != 0) {
                children.get(0).remove();
            }

            if (parent == null) return true;

            Tree.this.size--;
            return parent.children.remove(this);

        }

        /**
         * Convert tree to a string.
         *
         * @return The String-representation of the tree.
         */
        public String toString() {
            StringBuilder res = new StringBuilder("( " + value + ", [");
            for (int i = 0; i < children.size(); i++) {

                res.append(children.get(i).toString());

                if (i < children.size() - 1) {
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
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean res = false;
        for (T i : this) {
            if (i == o) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return this.toList().iterator();
    }

    @SuppressWarnings("unchecked")
    public Iterator<T> iteratorDFS() {
        class HelperClass extends ArrayList<Node> {
            public void foo(Node node) {
                this.add(node);
                for (Node i : node.children) {
                    foo(i);
                }
            }
        }

        HelperClass Q = new HelperClass();
        Q.foo(ROOT);
        Q.remove(0);

        List<T> q = new ArrayList<>();
        for (Node i : Q) {
            q.add((T) i.value);
        }
        return q.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.toList().toArray();
    }

    @SuppressWarnings("unchecked")
    public List<T> toList() {

        List<Node> Q = toNodeList();

        List<T> q = new ArrayList<>();
        for (Node i : Q) {
            q.add((T) i.value);
        }
        return q;
    }

    private List<Node> toNodeList() {
        List<Node> Q = new ArrayList<>();
        Q.add(ROOT);
        for (int i = 0; i < Q.size(); i++) {
            Q.addAll(Q.get(i).children);
        }
        Q.remove(0);
        return Q;
    }

    @Override
    public boolean add(T o) {
        ROOT.add(o);
        return true;
    }

    public Node nAdd(T o) {
        return ROOT.add(o);
    }

    public Node nAdd(Node node, T o) {
        return node.add(o);
    }

    @Override
    public boolean remove(Object o) throws ClassCastException {
        if (o.getClass() != Node.class) {
            throw new ClassCastException("Expected 'Node' class but found '" + o.getClass().toString() + "'.");
        }
        @SuppressWarnings("unchecked") boolean res = ((Node) o).remove();
        return res;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T i : c) {
            this.add(i);
        }
        return true;
    }

    @Override
    public void clear() {
        ROOT.remove();
    }

    @Override
    public boolean retainAll(Collection c) {
        for (Node i : this.toNodeList()) {
            if (!c.contains(i)) {
                i.remove();
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        for (Object i : c) {
            this.remove(i);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        boolean res = true;
        for (Object i : c) {
            if (!contains(i)) {
                res = false;
                break;
            }
        }
        return res;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray(Object[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return Arrays.copyOf(this.toArray(), size, a.getClass());
        System.arraycopy(this.toArray(), 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public String toString() {
        return ROOT.toString();
    }

}
