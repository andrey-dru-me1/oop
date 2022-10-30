package ru.nsu.fit.melnikov.oop.task_1_2_3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a weighted oriented graph data type with
 * vertices and edges by adjacency matrix.
 *
 * @param <V> type of vertices' value
 * @param <E> type of edges' value
 */
public class GraphAdjacencyMatrix<V, E> extends Graph<V, E> {

    Map<Vertex, Map<Vertex, Boolean>> adjMatrix;
    Set<Edge> edges;

    /**
     * Creates a graph object.
     */
    public GraphAdjacencyMatrix() {
        super();
        adjMatrix = new HashMap<>();
        edges = new HashSet<>();
    }

    @Override
    protected Vertex getVertex(V val) throws NoSuchElementException {
        return adjMatrix.keySet()
                .stream()
                .filter(v -> v.getValue().equals(val))
                .findFirst()
                .orElseThrow();
    }

    @Override
    protected Set<Edge> getVertexIncidents(Vertex v) {
        return edges
                .stream()
                .filter(e -> e.getVertexTo().equals(v) || e.getVertexFrom().equals(v))
                .collect(Collectors.toSet());
    }

    @Override
    protected Edge getEdge(E val) throws NoSuchElementException {
        return edges
                .stream()
                .filter(e -> e.getValue().equals(val))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public boolean addVertex(V val) {
        try {
            getVertex(val);
            return false;
        } catch (NoSuchElementException exc) {
            Map<Vertex, Boolean> helperMap = new HashMap<>();
            adjMatrix.keySet().forEach(v -> helperMap.put(v, false));
            Vertex newV = new Vertex(val);
            adjMatrix.put(newV, helperMap);
            adjMatrix.values().forEach(map -> map.put(newV, false));
            verticesCount++;
            return true;
        }
    }

    @Override
    public boolean addEdge(V vertexFrom, V vertexTo, Double w, E val) {
        Vertex from = getVertex(vertexFrom);
        Vertex to = getVertex(vertexTo);
        try {
            Edge e = getEdge(val);
            e.setWeight(w);
            this.setEdgeIncidents(val, vertexFrom, vertexTo);
            return false;
        } catch (NoSuchElementException exc) {
            Edge newE = new Edge(from, to, w, val);
            edges.add(newE);
            adjMatrix.get(from).replace(to, true);
            adjMatrix.get(to).replace(from, true);
            edgesCount++;
            return true;
        }
    }

    @Override
    public void removeEdge(E val) {
        Edge e = getEdge(val);
        edges.remove(e);
        adjMatrix.get(e.getVertexFrom()).replace(e.getVertexTo(), false);
        adjMatrix.get(e.getVertexTo()).replace(e.getVertexFrom(), false);
        edgesCount--;
    }

    @Override
    public void removeVertex(V val) {
        Vertex v = getVertex(val);
        this.getVertexIncidents(v).forEach(e -> this.removeEdge(e.getValue()));
        adjMatrix.remove(v);
        adjMatrix.values().forEach(map -> map.remove(v));
        verticesCount--;
    }

    @Override
    public void setEdgeIncidents(E val, V vertexFrom, V vertexTo) {
        Edge e = getEdge(val);
        Vertex from = getVertex(vertexFrom);
        Vertex to = getVertex(vertexTo);
        adjMatrix.get(e.getVertexFrom()).replace(e.getVertexTo(), false);
        adjMatrix.get(e.getVertexTo()).replace(e.getVertexFrom(), false);
        e.setVertices(from, to);
        adjMatrix.get(from).replace(to, true);
        adjMatrix.get(to).replace(from, true);
    }

    @Override
    public List<E> getEdgesByVertices(V vertexFrom, V vertexTo) {
        return edges
                .stream()
                .filter(e ->
                        e.getVertexTo()
                                .getValue()
                                .equals(vertexTo)
                                && e.getVertexFrom()
                                .getValue()
                                .equals(vertexFrom)
                )
                .map(Edge::getValue)
                .collect(Collectors.toList());
    }
}
