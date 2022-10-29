package ru.nsu.fit.melnikov.oop.task_1_2_3;

import java.util.*;

public class GraphAdjList<V, E> extends AbstractGraph<V, E> {

    Map<Vert, Set<Vert>> adjList;
    Set<Edge> edges;

    public GraphAdjList() {
        super();
        adjList = new HashMap<>();
        edges = new HashSet<>();
    }

    @Override
    protected Vert getV(V val) throws NoSuchElementException {
        return adjList.keySet()
                .stream()
                .filter(v -> v.getVal().equals(val))
                .findFirst()
                .orElseThrow();
    }

    @Override
    protected Edge getE(E val) throws NoSuchElementException {
        return edges
                .stream()
                .filter(e -> e.getVal().equals(val))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public boolean addV(V val) {
        try {
            getV(val);
            return false;
        } catch (NoSuchElementException exc) {
            adjList.put(new Vert(val), new HashSet<>());
            vCnt++;
            return true;
        }
    }

    @Override
    public boolean addE(V vFrom, V vTo, Double w, E val) {
        Vert from = getV(vFrom);
        Vert to = getV(vTo);
        try {

            Edge e = getE(val);

            adjList.get(e.getVFrom()).remove(e.getVTo());
            adjList.get(e.getVTo()).remove(e.getVFrom());

            e.setW(w);
            e.setVerts(from, to);

            adjList.get(from).add(to);
            adjList.get(to).add(from);

            return false;
        } catch (NoSuchElementException exc) {
            edges.add(new Edge(from, to, w, val));

            adjList.get(from).add(to);
            adjList.get(to).add(from);

            eCnt++;
            return true;
        }
    }

    @Override
    public void removeE(E val) {
        Edge e = getE(val);

        adjList.get(e.getVFrom()).remove(e.getVTo());
        adjList.get(e.getVTo()).remove(e.getVFrom());

        edges.remove(e);
        eCnt--;
    }

    @Override
    public void removeV(V val) {
        Vert v = getV(val);
        edges
                .stream()
                .filter(e -> e.getVTo().equals(v) || e.getVFrom().equals(v))
                .toList().forEach(e -> this.removeE(e.getVal()));
        adjList.remove(v);
        adjList.values().forEach(set -> set.remove(v));
        vCnt--;
    }

    @Override
    public void setEInceds(E val, V vFrom, V vTo) {
        Edge e = getE(val);
        adjList.get(e.getVFrom()).remove(e.getVTo());
        adjList.get(e.getVTo()).remove(e.getVFrom());
        Vert from = getV(vFrom);
        Vert to = getV(vTo);
        e.setVerts(from, to);
        adjList.get(from).add(to);
        adjList.get(to).add(from);
    }

    @Override
    public List<E> getEdgesByVerts(V vFrom, V vTo) {
        return edges
                .stream()
                .filter(e -> e.getVTo().getVal().equals(vTo) || e.getVFrom().getVal().equals(vFrom))
                .map(Edge::getVal)
                .toList();
    }
}
