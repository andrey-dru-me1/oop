package ru.nsu.fit.melnikov.task_1_2_2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
        List<Node> children;
        Node<T> parent;

        public Node(Node<T> parent, T value) {
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
            Node<T> newNode = new Node<>(this, value);
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
