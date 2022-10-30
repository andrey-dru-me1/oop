package ru.nsu.fit.melnikov.oop.task_1_2_3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a graph using the adjacency matrix.
 *
 * @param <V> Type of vertices values
 * @param <E> Type of edges values
 */
public class GraphAdjacencyList<V, E> extends Graph<V, E> {

    Map<Vertex, Set<Vertex>> adjList;
    Set<Edge> edges;

    /**
     * Creates new graph object.
     */
    public GraphAdjacencyList() {
        super();
        adjList = new HashMap<>();
        edges = new HashSet<>();
    }

    @Override
    protected Vertex getVertex(V val) throws NoSuchElementException {
        return adjList.keySet()
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
            adjList.put(new Vertex(val), new HashSet<>());
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

            adjList.get(e.getVertexFrom()).remove(e.getVertexTo());
            adjList.get(e.getVertexTo()).remove(e.getVertexFrom());

            e.setWeight(w);
            e.setVertices(from, to);

            adjList.get(from).add(to);
            adjList.get(to).add(from);

            return false;
        } catch (NoSuchElementException exc) {
            edges.add(new Edge(from, to, w, val));

            adjList.get(from).add(to);
            adjList.get(to).add(from);

            edgesCount++;
            return true;
        }
    }

    @Override
    public void removeEdge(E val) {
        Edge e = getEdge(val);

        adjList.get(e.getVertexFrom()).remove(e.getVertexTo());
        adjList.get(e.getVertexTo()).remove(e.getVertexFrom());

        edges.remove(e);
        edgesCount--;
    }

    @Override
    public void removeVertex(V val) {
        Vertex v = getVertex(val);
        edges
                .stream()
                .filter(e -> e.getVertexTo().equals(v) || e.getVertexFrom().equals(v))
                .collect(Collectors.toSet()).forEach(e -> this.removeEdge(e.getValue()));
        adjList.remove(v);
        adjList.values().forEach(set -> set.remove(v));
        verticesCount--;
    }

    @Override
    public void setEdgeIncidents(E val, V vertexFrom, V vertexTo) {
        Edge e = getEdge(val);
        adjList.get(e.getVertexFrom()).remove(e.getVertexTo());
        adjList.get(e.getVertexTo()).remove(e.getVertexFrom());
        Vertex from = getVertex(vertexFrom);
        Vertex to = getVertex(vertexTo);
        e.setVertices(from, to);
        adjList.get(from).add(to);
        adjList.get(to).add(from);
    }

    @Override
    public List<E> getEdgesByVertices(V vertexFrom, V vertexTo) {
        return edges
                .stream()
                .filter(
                        e -> e.getVertexTo().getValue().equals(vertexTo)
                                && e.getVertexFrom().getValue().equals(vertexFrom)
                )
                .map(Edge::getValue)
                .collect(Collectors.toList());
    }
}
