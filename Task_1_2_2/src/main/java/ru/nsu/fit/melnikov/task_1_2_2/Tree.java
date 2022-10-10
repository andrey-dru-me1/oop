package ru.nsu.fit.melnikov.task_1_2_2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a tree type. Has only root and its descendants.
 *
 * @param <T> Type of elements' values
 */
public class Tree<T> implements Collection<T> {

    private final Node root;
    private int size;

    public Tree() {
        root = new Node(null, null);
        size = 0;
    }

    /**
     * Represents a tree's nodes with their values and lists of sons.
     */
    public class Node {

        T value;
        List<Node> children;
        Node parent;

        public Node(Node parent, T value) {
            this.value = value;
            children = new ArrayList<>();
            this.parent = parent;
        }

        /**
         * Adds new child to current node with the specified value.
         *
         * @param value The value that will be bound on the new element.
         * @return The new node.
         */
        public boolean add(T value) {
            Node newNode = new Node(this, value);
            Tree.this.size++;
            return children.add(newNode);
        }

        /**
         * Removes current child with its descendants.
         */
        public boolean remove() {
            for (Node i : children) {
                i.remove();
            }

            if (parent == null) return true;

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
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        List<Node> Q = new ArrayList<>();
        Q.add(root);
        for (Node i : Q) {
            Q.addAll(i.children);
        }
        Q.remove(0);

        List<T> q = new ArrayList<>();
        for (Node i : Q) {
            q.add(i.value);
        }
        return q.iterator();
    }

    public Iterator<T> iteratorDFS() {
        class HelperClass extends ArrayList<Node> {
            public void foo(Node node) {
                this.addAll(node.children);
                for (Node i : node.children) {
                    foo(i);
                }
            }
        }

        HelperClass Q = new HelperClass();
        Q.foo(root);

        List<T> q = new ArrayList<>();
        for (Node i : Q) {
            q.add(i.value);
        }
        return q.iterator();
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

    public String toString() {
        return root.toString();
    }

}
