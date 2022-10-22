package ru.nsu.fit.melnikov.task_1_2_2;

import java.util.*;

/**
 * Represents a tree type.
 *
 * @param <T> Type of elements' values
 */
public class Tree<T> implements Collection<T> {

    /**
     * Null sentinel which points to significant nodes.
     */
    private final Node root;
    /**
     * Count of elements (nodes) in current tree without a root.
     */
    private int size;
    /**
     * Counter of assigned identifiers.
     */
    private int ids;
    private int modCount;

    public Tree() {
        root = new Node(null, null, 0);
        size = 0;
        ids = 1;
        modCount = 0;
    }

    /**
     * Represents a tree's node with its value, id and list of sons.
     */
    private class Node {

        /**
         * Value that user places in the tree.
         */
        T value;
        List<Node> children;
        Node parent;
        private final int id;

        public Node(Node parent, T value, int id) {
            this.value = value;
            children = new ArrayList<>();
            this.parent = parent;
            this.id = id;
        }

        /**
         * Changes the value of the current node to the new one.
         *
         * @param value Value to which current node's value will be changed
         */
        public void set(T value) {
            this.value = value;
        }

        /**
         * Returns the current node value.
         *
         * @return Current node value
         */
        public T get() {
            return value;
        }

        /**
         * Adds a new child to the current node with the specified value.
         *
         * @param value The value that will be bound on the new node
         * @return Index of the new node
         */
        public int add(T value) {
            Node newNode = new Node(this, value, ids++);
            children.add(newNode);
            Tree.this.size++;
            modCount++;
            return newNode.id;
        }

        /**
         * Removes current node with its descendants.
         *
         * @return Index of removed node
         */
        public int remove() {

            if (children == null) {
                return -1;   //checks if current node has already removed
            }

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
            modCount++;

            return this.id;

        }

        /**
         * Convert the tree to a string.
         *
         * @return The String-representation of the tree.
         */
        public String toString() {

            if (children == null) {
                return null;   //check if current node has already removed
            }

            String res = "( " + value + ", [";
            for (int i = 0; i < children.size(); i++) {

                res += children.get(i).toString();

                if (i < children.size() - 1) {
                    res += ", ";
                }
            }
            res += "] )";
            return res;
        }

    }

    /**
     * Sets a value of the node found by id to the new one.
     *
     * @param id    Identifier of node to modify
     * @param value Value to which the node has to be modified
     */
    public void setById(int id, T value) {
        getNodeById(id).set(value);
    }

    /**
     * Sets a value of the node to the new one.
     *
     * @param nodeValue Node with specific value to modify
     * @param newValue  Value to which the node has to be modified
     */
    public void set(T nodeValue, T newValue) {
        getNode(nodeValue).set(newValue);
    }

    /**
     * Returns the value of required node.
     *
     * @param id Identifier of node to get
     * @return Value of the required node
     */
    public T get(int id) {
        return getNode(id).get();
    }

    /**
     * Returns an identifier of the node specified by its value.
     *
     * @param value Value of node which id to return
     * @return Identifier of node specified by its value
     */
    public int getId(T value) {
        Iterator<Node> iter = this.nodeIterator();
        while (iter.hasNext()) {
            Node i = iter.next();
            if (i.id == 0) {
                continue;
            }
            if (i.value.equals(value)) {
                return i.id;
            }
        }
        throw new IndexOutOfBoundsException();
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
            if (i.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a breadth-search Iterator over this tree.
     *
     * @return A breadth-search Iterator over this tree.
     */
    @Override
    public Iterator<T> iterator() {
        return this.iteratorBFS();
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[this.size];
        int i = 0;
        for (T val : this) {
            res[i++] = val;
        }
        return res;
    }

    @Override
    public boolean add(T o) {
        addById(0, o);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        this.getNode(o).remove();
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAllById(0, c);
    }

    @Override
    public void clear() {
        root.remove();
    }

    @Override
    public boolean retainAll(Collection values) {
        List<Node> toRemove = new ArrayList<>();
        Iterator<Node> iter = this.nodeIterator();
        while (iter.hasNext()) {
            Node i = iter.next();
            if (i.id == 0) {
                continue;
            }
            if (!values.contains(i.value)) {
                toRemove.add(i);
            }
        }
        return removeNodes(toRemove);
    }

    @Override
    public boolean removeAll(Collection values) {
        List<Node> toRemove = new ArrayList<>();
        Iterator<Node> iter = this.nodeIterator();
        while (iter.hasNext()) {
            Node i = iter.next();
            if (i.id == 0) {
                continue;
            }
            if (values.contains(i.value)) {
                toRemove.add(i);
            }
        }
        return removeNodes(toRemove);
    }

    @Override
    public boolean containsAll(Collection values) {
        for (Object i : values) {
            if (!contains(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray(Object[] a) {
        if (a.length < size) {
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(this.toArray(), size, a.getClass());
        }
        System.arraycopy(this.toArray(), 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return (T[]) a;
    }

    /**
     * Removes all the nodes from list
     *
     * @param nodes List of nodes to remove
     * @return True if success
     */
    private boolean removeNodes(List<Node> nodes) {
        for (Node i : nodes) {
            i.remove();
        }
        return true;
    }

    /**
     * Returns a depth-search Iterator over this tree.
     *
     * @return A depth-search Iterator over this tree.
     */
    public Iterator<T> iteratorDFS() {
        return new TreeIterator<T>() {

            public T chooseNext() {
                Q.addAll(1, Q.get(0).children);
                return Q.remove(0).value;
            }
        };
    }

    /**
     * Returns a breadth-search Iterator over this tree.
     *
     * @return A breadth-search Iterator over this tree.
     */
    public Iterator<T> iteratorBFS() {
        return new TreeIterator<T>() {

            public T chooseNext() {
                Q.addAll(Q.get(0).children);
                return Q.remove(0).value;
            }
        };
    }

    /**
     * Creates a BFS iterator over all the nodes.
     *
     * @return BFS iterator over all the nodes
     */
    private Iterator<Node> nodeIterator() {
        return new TreeIterator<Node>() {

            @Override
            protected void addToQ() {
                Q.add(root);
            }

            public Node chooseNext() {
                Q.addAll(Q.get(0).children);
                return Q.remove(0);
            }
        };
    }

    /**
     * Helper class to not writing all the methods for each of BFS-, DFS- and Node-iterators.
     *
     * @param <E> Node or T
     */
    private abstract class TreeIterator<E> implements Iterator<E> {

        /**
         * Expected count of modifications. Required to catch when
         * a tree changes while iterating over it.
         */
        private final int expectedModCount;

        protected final List<Node> Q;

        private TreeIterator() {
            Q = new ArrayList<>(root.children);
            addToQ();
            expectedModCount = modCount;
        }

        /**
         * If some other class that extends this one need to iterate over the root.
         */
        protected void addToQ() {
        }

        @Override
        public boolean hasNext() {
            return Q.size() != 0;
        }

        @Override
        public E next() {
            checkForComodification();
            return chooseNext();
        }

        /**
         * DFS- and BFS-iterators has different algorithms to next element choose,
         * so they have to implement this method on their own.
         *
         * @return next value
         */
        protected abstract E chooseNext();

        /**
         * Checks if the tree is unchanged during iterating over it.
         *
         * @throws ConcurrentModificationException when the tree
         *                                         is changed while iterating over it
         */
        private void checkForComodification() throws ConcurrentModificationException {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

    }

    /**
     * Converts the tree to a list using breadth-search order.
     *
     * @return List with all values of the tree.
     */
    public List<T> toList() {
        return new ArrayList<>(this);
    }

    /**
     * Returns the node with id identifier.
     *
     * @param id Identifier of the node which value is required to get
     * @return Node with id identifier
     * @throws IndexOutOfBoundsException Throws when there isn't any
     *                                   node with the specified identifier.
     */
    private Node getNodeById(int id) throws IndexOutOfBoundsException {
        Iterator<Node> iter = this.nodeIterator();
        while (iter.hasNext()) {
            Node i = iter.next();
            if (i.id == id) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * returns a node with the queried value.
     *
     * @param value Value whose node to return
     * @return Node with the queried value
     * @throws IndexOutOfBoundsException Throws when there isn't any
     *                                   node with specified value.
     */
    private Node getNode(Object value) throws IndexOutOfBoundsException {
        Iterator<Node> iter = this.nodeIterator();
        while (iter.hasNext()) {
            Node i = iter.next();
            if (i.id == 0) {
                continue;
            }
            if (i.value.equals(value)) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Adds new node with the specified value to the tree and returns node's identifier.
     *
     * @param o Object to add
     * @return Identifier of a new node
     */
    public int addWithId(T o) {
        return addById(0, o);
    }

    /**
     * Adds new child node to the node of the tree specified by id and returns node's identifier.
     *
     * @param id Identifier of the node to which children o should be added
     * @param o  Object to add
     * @return Identifier of a new node
     */
    public int addById(int id, T o) {
        return this.getNodeById(id).add(o);
    }

    /**
     * Adds new child node to the node of the tree specified
     * by its value and returns node's identifier.
     *
     * @param parent Value of the node to which children o should be added
     * @param o      Object to add
     * @return Identifier of a new node
     */
    public int add(Object parent, T o) {
        return this.getNode(parent).add(o);
    }

    /**
     * Removes node of the tree specified by its id and returns the node's identifier.
     *
     * @param id Identifier of a node to remove
     * @return Identifier of the removed node
     */
    public int removeById(int id) {
        return this.getNodeById(id).remove();
    }

    /**
     * Removes a node of the tree specified by its value and returns the node's identifier.
     *
     * @param o Value of node to remove
     * @return Identifier of the removed node
     */
    public int removeWithId(Object o) {
        return this.getNode(o).remove();
    }

    /**
     * Adds all the elements in the specified collection
     * to a node of the tree specified by its identifier.
     *
     * @param id Identifier of a node to which children elements to add
     * @param c  Collection with elements to add
     * @return True if the tree changed as the result of call
     */
    public boolean addAllById(int id, Collection<? extends T> c) {
        for (T i : c) {
            getNodeById(id).add(i);
        }
        return true;
    }

    /**
     * Adds all the elements in the specified collection
     * to a node of the tree specified by its identifier.
     *
     * @param obj Value of a node to which children elements to add
     * @param c   Collection with elements to add
     * @return True if the tree changed as the result of call
     */
    public boolean addAll(T obj, Collection<? extends T> c) {
        for (T i : c) {
            getNode(obj).add(i);
        }
        return true;
    }

    /**
     * Returns a string representation of the tree.
     *
     * @return String representation of the tree
     */
    public String toString() {
        return root.toString();
    }

}
