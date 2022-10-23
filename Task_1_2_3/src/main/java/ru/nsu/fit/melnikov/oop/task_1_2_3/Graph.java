package ru.nsu.fit.melnikov.oop.task_1_2_3;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Graph<T> {

    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    private class Vertex {

        private static int ids = 0;
        private T value;
        private Set<Edge> inc_edges;
        private final int id;

        private Vertex(T value) {
            this.value = value;
            inc_edges = new HashSet<>();
            this.id = ids++;
        }

        public int getId() {
            return id;
        }

        public T getValue() {
            return value;
        }

        public Set<Edge> getInc_edges() {
            return inc_edges;
        }

        public T setValue(T value) {
            T prevVal = this.value;
            this.value = value;
            return prevVal;
        }

        public void addEdge(Edge edge) {
            inc_edges.add(edge);
        }

        public void addAllEdges(Collection<Edge> edges) {
            for (Edge edge : edges) {
                this.addEdge(edge);
            }
        }

    }

    private class Edge {

        private static int ids = 0;
        private Double weight;
        private Vertex from;
        private Vertex to;
        private final int id;

        private Edge(Vertex from, Vertex to, Double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.id = ids++;
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

        public boolean setVertices(List<Vertex> vertices) {
            if (vertices.size() != 2) {
                return false;
            }
            this.from = vertices.get(0);
            this.to = vertices.get(1);
            return true;
        }

    }

    public void addVertex(T value) {
        vertices.add(new Vertex(value));
    }

    public void addEdge(int v_from_id, int v_to_id, Double weight) {
        edges.add(
                new Edge(
                        getVertexById(v_from_id),
                        getVertexById(v_to_id),
                        weight
                )
        );
    }

    public void removeEdge(int id) {
        Edge e = getEdgeById(id);
        e.from.getInc_edges().remove(e);
        e.to.getInc_edges().remove(e);
        edges.remove(e);
    }

    public T removeVertex(int id) {
        Vertex v = getVertexById(id);
        T val = v.getValue();
        for (Edge e : v.getInc_edges()) {
            removeEdge(e.getId());
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
            prevVf.getInc_edges().remove(e);
            vf.getInc_edges().add(e);
        }
        if (prevVt != vt) {
            prevVt.getInc_edges().remove(e);
            vt.getInc_edges().add(e);
        }
        e.from = vf;
        e.to = vt;
    }

    public int getEdgeFrom(int id) {
        return getEdgeById(id).getFromVert().getId();
    }

    public int getEdgeTo(int id) {
        return getEdgeById(id).getToVert().getId();
    }

    private @NotNull Edge getEdgeById(int id) throws NoSuchElementException {
        for (Edge e : edges) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new NoSuchElementException();
    }

}
