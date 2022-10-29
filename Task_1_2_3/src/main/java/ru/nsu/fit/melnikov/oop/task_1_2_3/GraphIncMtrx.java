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

    private final Map<Vert, Set<Edge>> incMtrx;

    public GraphIncMtrx() {
        super();
        incMtrx = new HashMap<>();
    }

    @Override
    protected Vert getV(V val) throws NoSuchElementException {
        for (Vert v : incMtrx.keySet()) {
            if (v.getVal().equals(val)) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    protected Edge getE(E val) throws NoSuchElementException {
        for (Vert from : incMtrx.keySet()) {
            for (Edge e : incMtrx.get(from)) {
                if (e.getVal().equals(val)) {
                    return e;
                }
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean addV(V val) {
        try {
            getV(val);
            return false;
        } catch (NoSuchElementException excp) {
            incMtrx.put(new Vert(val), new HashSet<>());
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

            incMtrx.get(e.getVFrom()).remove(e);
            incMtrx.get(e.getVTo()).remove(e);

            e.setVerts(from, to);

            incMtrx.get(e.getVFrom()).add(e);
            incMtrx.get(e.getVTo()).add(e);

            e.setW(w);
            return false;
        } catch (NoSuchElementException exc) {

            Edge e = new Edge(from, to, w, val);

            incMtrx.get(from).add(e);
            incMtrx.get(to).add(e);

            eCnt++;
            return true;
        }
    }

    @Override
    public void removeE(E val) {
        Edge e = getE(val);
        incMtrx.get(e.getVFrom()).remove(e);
        incMtrx.get(e.getVTo()).remove(e);
        eCnt--;
    }

    @Override
    public void removeV(V val) {

        Vert v = getV(val);

        Set<Edge> toRemove = new HashSet<>(incMtrx.get(v));
        toRemove.forEach(e -> this.removeE(e.getVal()));

        incMtrx.remove(getV(val));
        vCnt--;
    }

    @Override
    public void setEInceds(E val, V vFrom, V vTo) {
        Edge e = getE(val);
        incMtrx.get(e.getVFrom()).remove(e);
        incMtrx.get(e.getVTo()).remove(e);
        Vert from = getV(vFrom);
        Vert to = getV(vTo);
        e.setVerts(from, to);
        incMtrx.get(from).add(e);
        incMtrx.get(to).add(e);
    }

    @Override
    public List<E> getEdgesByVerts(V vFrom, V vTo) {
        return incMtrx.get(getV(vFrom))
                .stream()
                .filter((e) -> e.getVTo().getVal().equals(vTo))
                .map(Edge::getVal).collect(Collectors.toList());
    }
}
