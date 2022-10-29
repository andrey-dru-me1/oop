package ru.nsu.fit.melnikov.oop.task_1_2_3;

import java.util.*;
import java.util.stream.Collectors;

public class GraphAdjMtrx<V, E> extends AbstractGraph<V, E> {

    Map<Vert, Map<Vert, Boolean>> adjMtrx;
    Set<Edge> edges;

    public GraphAdjMtrx() {
        super();
        adjMtrx = new HashMap<>();
        edges = new HashSet<>();
    }

    @Override
    protected Vert getV(V val) throws NoSuchElementException {
        return adjMtrx.keySet()
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
            Map<Vert, Boolean> hmap = new HashMap<>();
            adjMtrx.keySet().forEach(v -> hmap.put(v, false));
            Vert newV = new Vert(val);
            adjMtrx.put(newV, hmap);
            adjMtrx.values().forEach(map -> map.put(newV, false));
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
            e.setW(w);
            this.setEInceds(val, vFrom, vTo);
            return false;
        } catch (NoSuchElementException exc) {
            Edge newE = new Edge(from, to, w, val);
            edges.add(newE);
            adjMtrx.get(from).replace(to, true);
            adjMtrx.get(to).replace(from, true);
            eCnt++;
            return true;
        }
    }

    @Override
    public void removeE(E val) {
        Edge e = getE(val);
        edges.remove(e);
        adjMtrx.get(e.getVFrom()).replace(e.getVTo(), false);
        adjMtrx.get(e.getVTo()).replace(e.getVFrom(), false);
        eCnt--;
    }

    @Override
    public void removeV(V val) {
        Vert v = getV(val);
        edges
                .stream()
                .filter(e -> e.getVTo().getVal().equals(val) || e.getVFrom().getVal().equals(val))
                .collect(Collectors.toList())
                .forEach(e -> this.removeE(e.getVal()));
        adjMtrx.remove(v);
        adjMtrx.values().forEach(map -> map.remove(v));
        vCnt--;
    }

    @Override
    public void setEInceds(E val, V vFrom, V vTo) {
        Edge e = getE(val);
        Vert from = getV(vFrom);
        Vert to = getV(vTo);
        adjMtrx.get(e.getVFrom()).replace(e.getVTo(), false);
        adjMtrx.get(e.getVTo()).replace(e.getVFrom(), false);
        e.setVerts(from, to);
        adjMtrx.get(from).replace(to, true);
        adjMtrx.get(to).replace(from, true);
    }

    @Override
    public List<E> getEdgesByVerts(V vFrom, V vTo) {
        return edges
                .stream()
                .filter(e -> e.getVTo().getVal().equals(vTo) || e.getVFrom().getVal().equals(vFrom))
                .map(Edge::getVal)
                .collect(Collectors.toList());
    }
}
