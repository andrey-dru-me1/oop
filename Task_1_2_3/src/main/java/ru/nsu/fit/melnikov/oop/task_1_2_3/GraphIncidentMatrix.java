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
public class GraphIncidentMatrix<V, E> extends Graph<V, E> {

    private final Map<Vertex, Set<Edge>> incMatrix;

    public GraphIncidentMatrix() {
        super();
        incMatrix = new HashMap<>();
    }

    @Override
    protected Vertex getVertex(V val) throws NoSuchElementException {
        for (Vertex v : incMatrix.keySet()) {
            if (v.getValue().equals(val)) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    protected Set<Edge> getVertexIncidents(Vertex v) {
        return incMatrix.get(v);
    }

    @Override
    protected Edge getEdge(E val) throws NoSuchElementException {
        for (Vertex from : incMatrix.keySet()) {
            for (Edge e : incMatrix.get(from)) {
                if (e.getValue().equals(val)) {
                    return e;
                }
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean addVertex(V val) {
        try {
            getVertex(val);
            return false;
        } catch (NoSuchElementException exc) {
            incMatrix.put(new Vertex(val), new HashSet<>());
            verticesCount++;
            return true;
        }
    }

    @Override
    public boolean addEdge(V vertexFrom, V vertexTo, Double weight, E val) {

        Vertex from = getVertex(vertexFrom);
        Vertex to = getVertex(vertexTo);

        try {
            Edge e = getEdge(val);

            incMatrix.get(e.getVertexFrom()).remove(e);
            incMatrix.get(e.getVertexTo()).remove(e);

            e.setVertices(from, to);

            incMatrix.get(e.getVertexFrom()).add(e);
            incMatrix.get(e.getVertexTo()).add(e);

            e.setWeight(weight);
            return false;
        } catch (NoSuchElementException exc) {

            Edge e = new Edge(from, to, weight, val);

            incMatrix.get(from).add(e);
            incMatrix.get(to).add(e);

            edgesCount++;
            return true;
        }
    }

    @Override
    public void removeEdge(E val) {
        Edge e = getEdge(val);
        incMatrix.get(e.getVertexFrom()).remove(e);
        incMatrix.get(e.getVertexTo()).remove(e);
        edgesCount--;
    }

    @Override
    public void removeVertex(V val) {

        Vertex v = getVertex(val);

        Set<Edge> toRemove = new HashSet<>(incMatrix.get(v));
        toRemove.forEach(e -> this.removeEdge(e.getValue()));

        incMatrix.remove(getVertex(val));
        verticesCount--;
    }

    @Override
    public void setEdgeIncidents(E val, V vertexFrom, V vertexTo) {
        Edge e = getEdge(val);
        incMatrix.get(e.getVertexFrom()).remove(e);
        incMatrix.get(e.getVertexTo()).remove(e);
        Vertex from = getVertex(vertexFrom);
        Vertex to = getVertex(vertexTo);
        e.setVertices(from, to);
        incMatrix.get(from).add(e);
        incMatrix.get(to).add(e);
    }

    @Override
    public List<E> getEdgesByVertices(V vertexFrom, V vertexTo) {
        return incMatrix.get(getVertex(vertexFrom))
                .stream()
                .filter((e) -> e.getVertexTo().getValue().equals(vertexTo))
                .map(Edge::getValue).collect(Collectors.toList());
    }
}
