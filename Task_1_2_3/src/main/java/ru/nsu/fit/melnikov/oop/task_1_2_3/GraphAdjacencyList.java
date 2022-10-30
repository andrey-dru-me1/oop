package ru.nsu.fit.melnikov.oop.task_1_2_3;

import java.util.*;
import java.util.stream.Collectors;

public class GraphAdjacencyList<V, E> extends Graph<V, E> {

    Map<Vertex, Set<Vertex>> adjList;
    Set<Edge> edges;

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
    public boolean addEdge(V vFrom, V vTo, Double w, E val) {
        Vertex from = getVertex(vFrom);
        Vertex to = getVertex(vTo);
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
    public void setEdgeIncidents(E val, V vFrom, V vTo) {
        Edge e = getEdge(val);
        adjList.get(e.getVertexFrom()).remove(e.getVertexTo());
        adjList.get(e.getVertexTo()).remove(e.getVertexFrom());
        Vertex from = getVertex(vFrom);
        Vertex to = getVertex(vTo);
        e.setVertices(from, to);
        adjList.get(from).add(to);
        adjList.get(to).add(from);
    }

    @Override
    public List<E> getEdgesByVertices(V vFrom, V vTo) {
        return edges
                .stream()
                .filter(e -> e.getVertexTo().getValue().equals(vTo) && e.getVertexFrom().getValue().equals(vFrom))
                .map(Edge::getValue)
                .collect(Collectors.toList());
    }
}
