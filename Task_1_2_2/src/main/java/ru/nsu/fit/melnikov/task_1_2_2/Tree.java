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
    private int ids;

    public Tree() {
        ROOT = new Node(null, null, 0);
        size = 0;
        ids = 1;
    }

    /**
     * Represents a tree's nodes with their values and lists of sons.
     */
    private class Node {

        Object value;
        List<Node> children;
        Node parent;
        private final int ID;

        public Node(Node parent, T value, int id) {
            this.value = value;
            children = new ArrayList<>();
            this.parent = parent;
            ID = id;
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
        public int add(T value) {
            Node newNode = new Node(this, value, ids++);
            children.add(newNode);
            Tree.this.size++;
            return newNode.ID;
        }

        /**
         * Removes current child with its descendants.
         */
        public int remove() {

            if (children == null) return -1;   //checks if current node has already removed

            while (children.size() != 0) {
                children.get(0).remove();
            }

            if (parent != null) {
                Tree.this.size--;
                parent.children.remove(this);
            }

            this.value = null;
            this.children = null;
            this.parent = null;

            return this.ID;

        }

        /**
         * Convert tree to a string.
         *
         * @return The String-representation of the tree.
         */
        public String toString() {

            if (children == null) return null;   //check if current node has already removed

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

    public void setById(int index, T value) {
        getNode(index).set(value);
    }

    public void set(Object node, T value) {
        getNode(node).set(value);
    }

    public T get(int index) {
        return getNode(index).get();
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
        for (T i : this) {
            if (i.equals(o)) return true;
        }
        return false;
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
        Q.remove(0);

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
        return Q;
    }

    @Override
    public boolean add(T o) {
        addById(0, o);
        return true;
    }

    private Node getNodeById(int id) throws IndexOutOfBoundsException {
        for (Node i : this.toNodeList()) {
            if (i.ID == id) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    private Node getNode(Object value) throws IndexOutOfBoundsException {
        for (Node i : this.toNodeList()) {
            if (i.ID == 0) continue;
            if (i.value.equals(value)) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public int add2(T o) {
        return addById(0, o);
    }

    public int addById(int index, T o) {
        return this.getNodeById(index).add(o);
    }

    public int add(Object parent, T newNode) {
        return this.getNode(parent).add(newNode);
    }

    public int removeById(int index) throws ClassCastException {
        return this.getNodeById(index).remove();
    }

    @Override
    public boolean remove(Object o) throws ClassCastException {
        this.getNode(o).remove();
        return true;
    }

    public int remove2(Object o) throws ClassCastException {
        return this.getNode(o).remove();
    }

    public int getId(T value) {
        for (Node i : this.toNodeList()) {
            if (i.ID == 0) continue;
            if (i.value.equals(value)) return i.ID;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAllById(0, c);
    }

    public boolean addAllById(int id, Collection<? extends T> c) {
        for (T i : c) {
            getNodeById(id).add(i);
        }
        return true;
    }

    public boolean addAll(T obj, Collection<? extends T> c) {
        for (T i : c) {
            getNode(obj).add(i);
        }
        return true;
    }

    @Override
    public void clear() {
        ROOT.remove();
    }

    @Override
    public boolean retainAll(Collection values) {
        for (Node i : this.toNodeList()) {
            if (i.ID == 0) continue;
            if (!values.contains(i.value)) {
                i.remove();
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection values) {
        for (Node i : this.toNodeList()) {
            if (i.ID == 0) continue;
            if (values.contains(i.value)) {
                i.remove();
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection values) {
        for (Object i : values) {
            if (!contains(i)) return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray(Object[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(this.toArray(), size, a.getClass());
        System.arraycopy(this.toArray(), 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return (T[]) a;
    }

    public String toString() {
        return ROOT.toString();
    }

}
