package ru.nsu.fit.melnikov.oop.task_1_2_3;

import java.util.*;

/**
 * Represents a weighted oriented graph data type with
 * vertices and edges.
 *
 * @param <V> type of vertices' value
 * @param <E> type of edges' value
 */
public class Graph<V, E> {

    private final List<Vert> verts;
    private final List<Edge> edges;

    public Graph() {
        this.verts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * Represents vertex data type.
     */
    private class Vert {

        private V val;
        /**
         * Set of edges incident to the vertex
         */
        private final Set<Edge> incEdges;

        /**
         * Creates a new vertex with specific value.
         *
         * @param val vertex value
         */
        private Vert(V val) {
            this.val = val;
            incEdges = new HashSet<>();
            verts.add(this);
        }

        /**
         * Returns a set of vertex incident edges.
         *
         * @return set of vertex incident edges
         */
        public Set<Edge> getIncEdges() {
            return new HashSet<>(incEdges);
        }

        /**
         * Returns a value of the vertex.
         *
         * @return the vertex value
         */
        public V getVal() {
            return this.val;
        }

        /**
         * Changes current value of the vertex to the new one.
         *
         * @param val new value for the specified vertex
         * @return previous vertex value
         */
        public V setVal(V val) {
            V prevVal = this.val;
            this.val = val;
            return prevVal;
        }

        /**
         * Removes specific vertex.
         */
        public void remove() {
            List<Edge> toRemove = new ArrayList<>(this.incEdges);
            for (Edge e : toRemove) {
                e.remove();
            }
            verts.remove(this);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (!(o instanceof Graph.Vert)) {
                return false;
            }
            return ((Vert) o).val.equals(this.val);
        }

        /**
         * Adds specific edge to the incident edges set of the vertex
         *
         * @param edge edge to add to the incident edges set of the vertex
         */
        public void addEdge(Edge edge) {
            this.incEdges.add(edge);
        }

        /**
         * Adds all the edges from received collection
         * to the incident edges set of the vertex.
         *
         * @param edges collection of edges to add to the
         *              incident edges set of the vertex
         */
        public void addAllEdges(Collection<Edge> edges) {
            this.incEdges.addAll(edges);
        }

        /**
         * Removes specific edge from incident edges set of the vertex.
         *
         * @param edge edge to remove from incident edges set of the vertex
         * @return true if specific edge is removed
         */
        public boolean removeE(Edge edge) {
            return incEdges.remove(edge);
        }

    }

    /**
     * Represents the edge data type with incident vertices, weight and value.
     */
    private class Edge {

        /**
         * Weight of the edge
         */
        private Double w;
        private Vert from;
        private Vert to;
        private E val;

        /**
         * Creates new edge and adds it to the to and from vertices incident edges set.
         *
         * @param from vertex where edge starts
         * @param to   vertex where edge ends
         * @param w    edge weight
         * @param val  edge value
         */
        private Edge(Vert from, Vert to, Double w, E val) {
            this.from = from;
            this.to = to;
            this.w = w;
            this.val = val;
            from.addEdge(this);
            to.addEdge(this);
            edges.add(this);
        }

        /**
         * Returns an edge value.
         *
         * @return edge value
         */
        public E getVal() {
            return this.val;
        }

        /**
         * Returns an edge weight.
         *
         * @return edge weight
         */
        public Double getW() {
            return w;
        }

        /**
         * Returns a vertex where edge starts.
         *
         * @return vertex where edge starts
         */
        public Vert getVFrom() {
            return from;
        }

        /**
         * Returns a vertex where edge ends.
         *
         * @return vertex where edge ends
         */
        public Vert getVTo() {
            return to;
        }

        /**
         * Changes an edge value.
         *
         * @param val new edge value
         * @return previous edge value
         */
        public E setVal(E val) {
            E prevVal = this.val;
            this.val = val;
            return prevVal;
        }

        /**
         * Changes an edge weight.
         *
         * @param w new edge weight
         * @return previous edge weight
         */
        public Double setW(Double w) {
            Double prevW = this.w;
            this.w = w;
            return prevW;
        }

        /**
         * Changes the vertex where the edge begins.
         *
         * @param v new vertex where the edge begins
         */
        public void setVFrom(Vert v) {
            this.from = v;
        }

        /**
         * Changes the vertex where the edge ends.
         *
         * @param v new vertex where the edge ends
         */
        public void setVTo(Vert v) {
            this.to = v;
        }

        /**
         * Changes both {@code from} and {@code to} edge incident vertices.
         *
         * @param from vertex where the edge starts
         * @param to   vertex where the edge ends
         */
        public void setVerts(Vert from, Vert to) {
            this.setVFrom(from);
            this.setVTo(to);
        }

        /**
         * Removes current edge from the graph.
         */
        public void remove() {
            from.removeE(this);
            to.removeE(this);
            edges.remove(this);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (!(o instanceof Graph.Edge)) {
                return false;
            }
            Edge e = (Edge) o;
            return (
                    e.w.equals(this.w) &&
                            e.from.equals(this.from) &&
                            e.to.equals(this.to) &&
                            e.val.equals(this.val)
            );
        }

    }

    /**
     * Returns a vertex with the specific value.
     *
     * @param val value of vertex to return
     * @return vertex with the specific value
     * @throws NoSuchElementException if there is no any vertex with the specific value
     */
    private Vert getV(V val) throws NoSuchElementException {
        for (Vert v : verts) {
            if (v.getVal().equals(val)) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Checks if the graph contains the specific vertex.
     *
     * @param val value to check if the graph contains it
     * @return true if graph contains vertex with specific value
     */
    public boolean containsV(V val) {
        try {
            getV(val);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Returns an edge with the specific value.
     *
     * @param val value of edge to return
     * @return edge with the specific value
     * @throws NoSuchElementException if there is no any edge with the specific value
     */
    private Edge getE(E val) throws NoSuchElementException {
        for (Edge e : edges) {
            if (e.getVal().equals(val)) {
                return e;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Checks if the graph contains the specific edge.
     *
     * @param val value to check if the graph contains it
     * @return true if graph contains edge with specific value
     */
    public boolean containsE(E val) {
        try {
            getE(val);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Adds a vertex with the specific value to the graph.
     *
     * @param val value of vertex to add
     * @return false if graph is already contains the vertex with the specific value
     */
    public boolean addV(V val) {
        if (containsV(val)) {
            return false;
        }
        Vert v = new Vert(val);
        return true;
    }

    /**
     * Adds new vertices with specific values to the graph.
     *
     * @param vals values to add to the graph
     * @return list of boolean for each input value: false if such element
     * is already in the graph
     */
    public List<Boolean> addVerts(V[] vals) {
        List<Boolean> res = new ArrayList<>();
        for (V val : vals) {
            res.add(addV(val));
        }
        return res;
    }

    /**
     * Adds edge with specific value to the graph.
     *
     * @param vFrom vertex where edge begins
     * @param vTo   vertex where edge ends
     * @param w     weight of a new edge
     * @param val   value of a new edge
     * @return false if edge with the specific value is already
     * in the graph.
     */
    public boolean addE(V vFrom, V vTo, Double w, E val) {

        Vert from = getV(vFrom);
        Vert to = getV(vTo);

        Edge e;
        try {
            e = getE(val);
        } catch (NoSuchElementException exc) {
            e = new Edge(from, to, w, val);
            return true;
        }
        e.setVFrom(from);
        e.setVTo(to);
        e.setW(w);
        return false;
    }

    /**
     * Removes edge with the specific value from the graph.
     *
     * @param val value of edge to remove
     */
    public void removeE(E val) {
        getE(val).remove();
    }

    /**
     * Removes edge with the specific value from the graph.
     *
     * @param val value of vertex to remove
     */
    public void removeV(V val) {
        getV(val).remove();
    }

    /**
     * Changes value of the specific vertex of the graph.
     *
     * @param prevVal value of vertex to change
     * @param newVal  value to which specific vertex has to change
     */
    public void setVVal(V prevVal, V newVal) {
        Vert v = getV(prevVal);
        v.setVal(newVal);
    }

    /**
     * Changes weight of the specific edge.
     *
     * @param val value of edge to modify
     * @param w   new weight of the specific edge
     * @return previous weight of the specific edge
     */
    public Double setEdgeW(E val, Double w) {
        return getE(val).setW(w);
    }

    /**
     * Changes value of the specific edge.
     *
     * @param prevVal value of edge to change
     * @param newVal  value to which specific edge has to change
     */
    public void setEVal(E prevVal, E newVal) {
        getE(prevVal).setVal(newVal);
    }

    /**
     * Changes incident vertices of the specific edge.
     *
     * @param val   value of the edge to change
     * @param vFrom vertex where edge has to begin
     * @param vTo   vertex where edge has to end
     */
    public void setEInceds(E val, V vFrom, V vTo) {
        Edge e = getE(val);
        Vert from = getV(vFrom);
        Vert to = getV(vTo);
        e.setVerts(from, to);
    }

    /**
     * Returns vertex where edge with the specific value begins.
     *
     * @param val value of edge
     * @return vertex where edge with the specific value begins
     */
    public V getEFrom(E val) {
        return getE(val).getVFrom().getVal();
    }

    /**
     * Returns vertex where edge with the specific value ends.
     *
     * @param val value of edge
     * @return vertex where edge with the specific value ends
     */
    public V getETo(E val) {
        return getE(val).getVTo().getVal();
    }

    /**
     * Returns weight of edge with the specific value.
     *
     * @param val value of edge
     * @return weight of edge with the specific value
     */
    public Double getEdgeW(E val) {
        return getE(val).getW();
    }

    /**
     * Returns list of edges which starts with vFrom and ends with vTo vertices.
     *
     * @param vFrom start vertex of edge to get
     * @param vTo   end vertex of edge to get
     * @return list of edges which starts with vFrom and ends with vTo vertices
     */
    public List<E> getEdgesByVerts(V vFrom, V vTo) {
        Vert from = getV(vFrom);
        Vert to = getV(vTo);

        List<E> res = new ArrayList<>();
        for (Edge e : from.getIncEdges()) {
            if (e.getVTo().equals(to)) {
                res.add(e.getVal());
            }
        }
        return res;
    }

    /**
     * Returns count of vertex in the graph.
     *
     * @return count of vertex in the graph
     */
    public int VCnt() {
        return verts.size();
    }

    /**
     * Returns count of edge in the graph.
     *
     * @return count of edge in the graph
     */
    public int ECnt() {
        return edges.size();
    }

}
