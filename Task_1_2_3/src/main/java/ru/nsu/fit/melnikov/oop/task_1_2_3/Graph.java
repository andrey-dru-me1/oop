package ru.nsu.fit.melnikov.oop.task_1_2_3;

import java.util.*;

public class Graph<V, E> {

    private final List<Vert> verts;
    private final List<Edge> edges;

    public Graph() {
        this.verts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    private class Vert {

        private V val;
        private final Set<Edge> incEdges;

        private Vert(V val) {
            this.val = val;
            incEdges = new HashSet<>();
            verts.add(this);
        }

        public Set<Edge> getIncEdges() {
            return new HashSet<>(incEdges);
        }

        public V getVal() {
            return this.val;
        }

        public V setVal(V val) {
            V prevVal = this.val;
            this.val = val;
            return prevVal;
        }

        public V remove() {
            List<Edge> toRemove = new ArrayList<>(this.incEdges);
            for (Edge e : toRemove) {
                e.remove();
            }
            verts.remove(this);
            return this.val;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (!(o instanceof Graph.Vert)) {
                return false;
            }
            return ((Vert) o).val.equals(this.val);
        }

        public void addEdge(Edge edge) {
            this.incEdges.add(edge);
        }

        public void addAllEdges(Collection<Edge> edges) {
            this.incEdges.addAll(edges);
        }

        public boolean removeE(Edge edge) {
            return incEdges.remove(edge);
        }

    }

    private class Edge {

        private Double w;
        private Vert from;
        private Vert to;
        private E val;

        private Edge(Vert from, Vert to, Double w, E val) {
            this.from = from;
            this.to = to;
            this.w = w;
            this.val = val;
            from.addEdge(this);
            to.addEdge(this);
            edges.add(this);
        }

        public E getVal() {
            return this.val;
        }

        public Double getW() {
            return w;
        }

        public Vert getVFrom() {
            return from;
        }

        public Vert getVTo() {
            return to;
        }

        public E setVal(E val) {
            E prevVal = this.val;
            this.val = val;
            return prevVal;
        }

        public Double setW(Double w) {
            Double prevW = this.w;
            this.w = w;
            return prevW;
        }

        public void setVFrom(Vert v) {
            this.from = v;
        }

        public void setVTo(Vert v) {
            this.to = v;
        }

        public void setVerts(Vert from, Vert to) {
            this.setVFrom(from);
            this.setVTo(to);
        }

        public E remove() throws NoSuchElementException {
            from.removeE(this);
            to.removeE(this);
            edges.remove(this);
            return this.val;
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

    private Vert getV(V val) throws NoSuchElementException {
        for (Vert v : verts) {
            if (v.getVal().equals(val)) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean containsV(V val) {
        try {
            getV(val);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private Edge getE(E val) throws NoSuchElementException {
        for (Edge e : edges) {
            if (e.getVal().equals(val)) {
                return e;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean containsE(E val) {
        try {
            getE(val);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean addV(V val) {
        if (containsV(val)) {
            return false;
        }
        Vert v = new Vert(val);
        return true;
    }

    public List<Boolean> addVerts(V[] vals) {
        List<Boolean> res = new ArrayList<>();
        for (V val : vals) {
            res.add(addV(val));
        }
        return res;
    }

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

    public E removeE(E val) throws NoSuchElementException {
        return getE(val).remove();
    }

    public V removeV(V val) {
        return getV(val).remove();
    }

    public void setVVal(V prevVal, V newVal) {
        Vert v = getV(prevVal);
        v.setVal(newVal);
    }

    public Double setEdgeW(E val, Double w) {
        return getE(val).setW(w);
    }

    public void setEVal(E prevVal, E newVal) {
        getE(prevVal).setVal(newVal);
    }

    public void setEInceds(E val, V vFrom, V vTo) {
        Edge e = getE(val);
        Vert from = getV(vFrom);
        Vert to = getV(vTo);
        e.setVerts(from, to);
    }

    public V getEFrom(E val) {
        return getE(val).getVFrom().getVal();
    }

    public V getETo(E val) {
        return getE(val).getVTo().getVal();
    }

    public Double getEdgeW(E val) {
        return getE(val).getW();
    }

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

    public int VCnt() {
        return verts.size();
    }

    public int ECnt() {
        return edges.size();
    }

}
