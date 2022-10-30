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
public class GraphIncMtrx<V, E> extends AbstractGraph<V, E> {

    private final Map<Vertex, Set<Edge>> incMtrx;

    public GraphIncMtrx() {
        super();
        incMtrx = new HashMap<>();
    }

    @Override
    protected Vertex getVertex(V val) throws NoSuchElementException {
        for (Vertex v : incMtrx.keySet()) {
            if (v.getValue().equals(val)) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    protected Set<Edge> getVertexIncidents(Vertex v) {
        return incMtrx.get(v);
    }

    @Override
    protected Edge getEdge(E val) throws NoSuchElementException {
        for (Vertex from : incMtrx.keySet()) {
            for (Edge e : incMtrx.get(from)) {
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
        } catch (NoSuchElementException excp) {
            incMtrx.put(new Vertex(val), new HashSet<>());
            verticesCount++;
            return true;
        }
    }

    @Override
    public boolean addEdge(V vFrom, V vTo, Double weight, E val) {

        Vertex from = getVertex(vFrom);
        Vertex to = getVertex(vTo);

        try {
            Edge e = getEdge(val);

            incMtrx.get(e.getVertexFrom()).remove(e);
            incMtrx.get(e.getVertexTo()).remove(e);

            e.setVertices(from, to);

            incMtrx.get(e.getVertexFrom()).add(e);
            incMtrx.get(e.getVertexTo()).add(e);

            e.setWeight(weight);
            return false;
        } catch (NoSuchElementException exc) {

            Edge e = new Edge(from, to, weight, val);

            incMtrx.get(from).add(e);
            incMtrx.get(to).add(e);

            edgesCount++;
            return true;
        }
    }

    @Override
    public void removeEdge(E val) {
        Edge e = getEdge(val);
        incMtrx.get(e.getVertexFrom()).remove(e);
        incMtrx.get(e.getVertexTo()).remove(e);
        edgesCount--;
    }

    @Override
    public void removeVertex(V val) {

        Vertex v = getVertex(val);

        Set<Edge> toRemove = new HashSet<>(incMtrx.get(v));
        toRemove.forEach(e -> this.removeEdge(e.getValue()));

        incMtrx.remove(getVertex(val));
        verticesCount--;
    }

    @Override
    public void setEdgeIncidents(E val, V vFrom, V vTo) {
        Edge e = getEdge(val);
        incMtrx.get(e.getVertexFrom()).remove(e);
        incMtrx.get(e.getVertexTo()).remove(e);
        Vertex from = getVertex(vFrom);
        Vertex to = getVertex(vTo);
        e.setVertices(from, to);
        incMtrx.get(from).add(e);
        incMtrx.get(to).add(e);
    }

    @Override
    public List<E> getEdgesByVertices(V vFrom, V vTo) {
        return incMtrx.get(getVertex(vFrom))
                .stream()
                .filter((e) -> e.getVertexTo().getValue().equals(vTo))
                .map(Edge::getValue).collect(Collectors.toList());
    }
}
