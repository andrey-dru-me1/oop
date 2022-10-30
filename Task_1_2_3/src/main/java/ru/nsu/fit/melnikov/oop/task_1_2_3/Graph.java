package ru.nsu.fit.melnikov.oop.task_1_2_3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a weighted oriented graph data type with
 * vertices and edges.
 *
 * @param <V> type of vertices' value
 * @param <E> type of edges' value
 */
abstract class Graph<V, E> {

    protected Integer verticesCount;
    protected Integer edgesCount;

    public Graph() {
        verticesCount = 0;
        edgesCount = 0;
    }

    /**
     * Represents vertex data type.
     */
    protected class Vertex {

        private V value;

        /**
         * Creates a new vertex with specific value.
         *
         * @param value vertex value
         */
        protected Vertex(V value) {
            this.value = value;
        }

        /**
         * Returns a value of the vertex.
         *
         * @return the vertex value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Changes current value of the vertex to the new one.
         *
         * @param value new value for the specified vertex
         */
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (!(o instanceof Graph<?, ?>.Vertex)) {
                return false;
            }
            return ((Vertex) o).getValue().equals(this.value);
        }

    }

    /**
     * Represents the edge data type with incident vertices, weight and value.
     */
    protected class Edge {

        /**
         * Weight of the edge.
         */
        private Double weight;
        private Vertex from;
        private Vertex to;
        private E value;

        /**
         * Creates new edge and adds it to the 'to' and 'from' vertices incident edges set.
         *
         * @param from   vertex where edge starts
         * @param to     vertex where edge ends
         * @param weight edge weight
         * @param value  edge value
         */
        protected Edge(Vertex from, Vertex to, Double weight, E value) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.value = value;
        }

        /**
         * Returns an edge value.
         *
         * @return edge value
         */
        public E getValue() {
            return this.value;
        }

        /**
         * Returns an edge weight.
         *
         * @return edge weight
         */
        public Double getWeight() {
            return weight;
        }

        /**
         * Returns a vertex where edge starts.
         *
         * @return vertex where edge starts
         */
        public Vertex getVertexFrom() {
            return from;
        }

        /**
         * Returns a vertex where edge ends.
         *
         * @return vertex where edge ends
         */
        public Vertex getVertexTo() {
            return to;
        }

        /**
         * Changes an edge value.
         *
         * @param value new edge value
         */
        public void setValue(E value) {
            this.value = value;
        }

        /**
         * Changes an edge weight.
         *
         * @param weight new edge weight
         * @return previous edge weight
         */
        public Double setWeight(Double weight) {
            Double prevW = this.weight;
            this.weight = weight;
            return prevW;
        }

        /**
         * Changes the vertex where the edge begins.
         *
         * @param v new vertex where the edge begins
         */
        public void setVertexFrom(Vertex v) {
            this.from = v;
        }

        /**
         * Changes the vertex where the edge ends.
         *
         * @param v new vertex where the edge ends
         */
        public void setVertexTo(Vertex v) {
            this.to = v;
        }

        /**
         * Changes both {@code from} and {@code to} edge incident vertices.
         *
         * @param from vertex where the edge starts
         * @param to   vertex where the edge ends
         */
        public void setVertices(Vertex from, Vertex to) {
            this.setVertexFrom(from);
            this.setVertexTo(to);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (!(o instanceof Graph<?, ?>.Edge)) {
                return false;
            }
            Edge e = (Edge) o;
            return (
                    e.weight.equals(this.weight)
                            && e.from.equals(this.from)
                            && e.to.equals(this.to)
                            && e.value.equals(this.value)
            );
        }

    }

    /**
     * Returns a vertex with the specific value.
     *
     * @param value value of vertex to return
     * @return vertex with the specific value
     * @throws NoSuchElementException if there is no any vertex with the specific value
     */
    protected abstract Vertex getVertex(V value) throws NoSuchElementException;

    protected abstract Set<Edge> getVertexIncidents(Vertex v);

    protected Set<Edge> getVertexIncidentsOut(Vertex v) {
        return this.getVertexIncidents(v)
                .stream()
                .filter(e -> e.getVertexFrom().equals(v))
                .collect(Collectors.toSet());
    }

    /**
     * Checks if the graph contains the specific vertex.
     *
     * @param value value to check if the graph contains it
     * @return true if graph contains vertex with specific value
     */
    public boolean containsVertex(V value) {
        try {
            getVertex(value);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Returns an edge with the specific value.
     *
     * @param value value of edge to return
     * @return edge with the specific value
     * @throws NoSuchElementException if there is no any edge with the specific value
     */
    protected abstract Edge getEdge(E value) throws NoSuchElementException;

    /**
     * Checks if the graph contains the specific edge.
     *
     * @param value value to check if the graph contains it
     * @return true if graph contains edge with specific value
     */
    public boolean containsEdge(E value) {
        try {
            getEdge(value);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Adds a vertex with the specific value to the graph.
     *
     * @param value value of vertex to add
     * @return false if graph is already contains the vertex with the specific value
     */
    public abstract boolean addVertex(V value);

    /**
     * Adds new vertices with specific values to the graph.
     *
     * @param values values to add to the graph
     * @return list of boolean for each input value: false if such element
     *      is already in the graph
     */
    public List<Boolean> addVertices(V[] values) {
        List<Boolean> res = new ArrayList<>();
        for (V value : values) {
            res.add(addVertex(value));
        }
        return res;
    }

    /**
     * Adds edge with specific value to the graph.
     *
     * @param vertexFrom vertex where edge begins
     * @param vertexTo   vertex where edge ends
     * @param weight     weight of a new edge
     * @param value      value of a new edge
     * @return false if edge with the specific value is already
     * in the graph.
     */
    public abstract boolean addEdge(V vertexFrom, V vertexTo, Double weight, E value);

    /**
     * Removes edge with the specific value from the graph.
     *
     * @param value value of edge to remove
     */
    public abstract void removeEdge(E value);

    /**
     * Removes edge with the specific value from the graph.
     *
     * @param value value of vertex to remove
     */
    public abstract void removeVertex(V value);

    /**
     * Changes value of the specific vertex of the graph.
     *
     * @param prevVal value of vertex to change
     * @param newVal  value to which specific vertex has to change
     */
    public void setVertexValue(V prevVal, V newVal) {
        Vertex v = getVertex(prevVal);
        v.setValue(newVal);
    }

    /**
     * Changes weight of the specific edge.
     *
     * @param value value of edge to modify
     * @param weight   new weight of the specific edge
     * @return previous weight of the specific edge
     */
    public Double setEdgeWeight(E value, Double weight) {
        return getEdge(value).setWeight(weight);
    }

    /**
     * Changes value of the specific edge.
     *
     * @param prevVal value of edge to change
     * @param newVal  value to which specific edge has to change
     */
    public void setEdgeValue(E prevVal, E newVal) {
        getEdge(prevVal).setValue(newVal);
    }

    /**
     * Changes incident vertices of the specific edge.
     *
     * @param value value of the edge to change
     * @param vFrom vertex where edge has to begin
     * @param vTo   vertex where edge has to end
     */
    public abstract void setEdgeIncidents(E value, V vFrom, V vTo);

    /**
     * Returns vertex where edge with the specific value begins.
     *
     * @param value value of edge
     * @return vertex where edge with the specific value begins
     */
    public V getEdgeFrom(E value) {
        return getEdge(value).getVertexFrom().getValue();
    }

    /**
     * Returns vertex where edge with the specific value ends.
     *
     * @param value value of edge
     * @return vertex where edge with the specific value ends
     */
    public V getEdgeTo(E value) {
        return getEdge(value).getVertexTo().getValue();
    }

    /**
     * Returns weight of edge with the specific value.
     *
     * @param value value of edge
     * @return weight of edge with the specific value
     */
    public Double getEdgeWeight(E value) {
        return getEdge(value).getWeight();
    }

    /**
     * Returns list of edges which starts with vFrom and ends with vTo vertices.
     *
     * @param vFrom start vertex of edge to get
     * @param vTo   end vertex of edge to get
     * @return list of edges which starts with vFrom and ends with vTo vertices
     */
    public abstract List<E> getEdgesByVertices(V vFrom, V vTo);

    /**
     * Returns count of vertex in the graph.
     *
     * @return count of vertex in the graph
     */
    public int getVerticesCount() {
        return verticesCount;
    }

    /**
     * Returns count of edge in the graph.
     *
     * @return count of edge in the graph
     */
    public int getEdgesCount() {
        return edgesCount;
    }

    public List<V> sort(V start) throws NegativeLoopException {
        Vertex s = getVertex(start);
        Map<Vertex, Double> d = new HashMap<>();
        d.put(s, 0.0);
        Queue<Edge> q = new ArrayDeque<>(this.getVertexIncidentsOut(s));
        int iV = 0;
        while (!q.isEmpty()) {
            if (iV >= getVerticesCount() * getVerticesCount()) {
                throw new NegativeLoopException();
            }
            Edge cur = q.remove();
            Vertex to = cur.getVertexTo();
            Double newD = d.get(cur.getVertexFrom()) + cur.getWeight();
            if (!d.containsKey(to) || d.get(to) > newD) {
                d.put(to, newD);
                q.addAll(this.getVertexIncidentsOut(to));
            }
            iV++;
        }
        return d.keySet()
                .stream()
                .sorted((v1, v2) -> (int) (d.get(v1) - d.get(v2)))
                .map(Vertex::getValue)
                .collect(Collectors.toList());
    }

}