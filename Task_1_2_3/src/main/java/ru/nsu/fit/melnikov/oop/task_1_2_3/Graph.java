package ru.nsu.fit.melnikov.oop.task_1_2_3;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Graph<T> {

    private final List<Vertex> vertices;
    private final List<Edge> edges;

    private int vids;
    private int eids;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        vids = 0;
        eids = 0;
    }

    private class Vertex {

        private T value;
        private final Set<Edge> incEdges;
        private final int id;

        private Vertex(T value) {
            this.value = value;
            incEdges = new HashSet<>();
            this.id = vids++;
        }

        public int getId() {
            return id;
        }

        public T getValue() {
            return value;
        }

        public Set<Edge> getIncEdges() {
            return incEdges;
        }

        public T setValue(T value) {
            T prevVal = this.value;
            this.value = value;
            return prevVal;
        }

        public void addEdge(Edge edge) {
            incEdges.add(edge);
        }

        public void addAllEdges(Collection<Edge> edges) {
            for (Edge edge : edges) {
                this.addEdge(edge);
            }
        }

    }

    private class Edge {

        private Double weight;
        private Vertex from;
        private Vertex to;
        private final int id;

        private Edge(Vertex from, Vertex to, Double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.id = eids++;
            from.incEdges.add(this);
            to.incEdges.add(this);
        }

        public int getId() {
            return id;
        }

        public Double getWeight() {
            return weight;
        }

        public Vertex getFromVert() {
            return from;
        }

        public Vertex getToVert() {
            return to;
        }

        public List<Vertex> getVertices() {
            return Arrays.asList(from, to);
        }

        public Double setWeight(Double weight) {
            Double prevW = this.weight;
            this.weight = weight;
            return prevW;
        }

        public void setFrom(Vertex vertex) {
            this.from = vertex;
        }

        public void setTo(Vertex to) {
            this.to = to;
        }

    }

    public int addVertex(T value) {
        Vertex v = new Vertex(value);
        vertices.add(v);
        return v.getId();
    }

    public List<Integer> addVertices(T[] values) {
        List<Integer> l = new ArrayList<>();
        for (T i : values) {
            l.add(addVertex(i));
        }
        return l;
    }

    public int addEdge(int v_from_id, int v_to_id, Double weight) {
        Edge e = new Edge(
                getVertexById(v_from_id),
                getVertexById(v_to_id),
                weight
        );
        edges.add(e);
        return e.getId();
    }

    public void removeEdge(int id) {
        Edge e = getEdgeById(id);
        e.from.getIncEdges().remove(e);
        e.to.getIncEdges().remove(e);
        edges.remove(e);
    }

    public T removeVertex(int id) {
        Vertex v = getVertexById(id);
        T val = v.getValue();

        List<Integer> toRemove = new ArrayList<>();
        for (Edge e : v.getIncEdges()) {
            toRemove.add(e.getId());
        }
        toRemove.sort((x, y) -> y - x);
        for (int i : toRemove) {
            removeEdge(i);
        }
        vertices.remove(v);
        return val;
    }

    public T setVertexValue(int id, T value) {
        return getVertexById(id).setValue(value);
    }

    public T getVertexValue(int id) {
        return getVertexById(id).getValue();
    }

    public List<Integer> getVerticesWithValue(T value) {
        List<Integer> l = new ArrayList<>();
        for (Vertex i : this.vertices) {
            if (i.getValue().equals(value)) {
                l.add(i.getId());
            }
        }
        return l;
    }

    public List<Integer> getEdgesByVerts(int v_from_id, int v_to_id) {
        List<Integer> l = new ArrayList<>();
        for (Edge i : this.edges) {
            if (i.getFromVert().getId() == v_from_id && i.getToVert().getId() == v_to_id) {
                l.add(i.getId());
            }
        }
        return l;
    }

    private @NotNull Vertex getVertexById(int id) throws NoSuchElementException {
        for (Vertex v : vertices) {
            if (v.getId() == id) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    public Double setEdgeWeight(int id, Double weight) {
        return getEdgeById(id).setWeight(weight);
    }

    public void setEdgeInceds(int e_id, int v_from_id, int v_to_id) {
        Edge e = getEdgeById(e_id);
        Vertex vf = getVertexById(v_from_id);
        Vertex vt = getVertexById(v_to_id);
        Vertex prevVf = e.getFromVert();
        Vertex prevVt = e.getToVert();
        if (prevVf != vf) {
            prevVf.getIncEdges().remove(e);
            vf.getIncEdges().add(e);
        }
        if (prevVt != vt) {
            prevVt.getIncEdges().remove(e);
            vt.getIncEdges().add(e);
        }
        e.setFrom(vf);
        e.setTo(vt);
    }

    public int getEdgeFrom(int id) {
        return getEdgeById(id).getFromVert().getId();
    }

    public int getEdgeTo(int id) {
        return getEdgeById(id).getToVert().getId();
    }

    public Double getEdgeWeight(int id) {
        return getEdgeById(id).getWeight();
    }

    private @NotNull Edge getEdgeById(int id) throws NoSuchElementException {
        for (Edge e : edges) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new NoSuchElementException();
    }

    public int VerticesCount() {
        return vertices.size();
    }

    public int EdgesCount() {
        return edges.size();
    }

}
