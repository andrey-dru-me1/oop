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

        public void setValue(T value) {
            this.value = value;
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

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        public void setFrom(Vertex vertex) {
            this.from = vertex;
        }

        public void setTo(Vertex to) {
            this.to = to;
        }

        public boolean setVertices(List<Vertex> vertices) {
            if (vertices.size() > 2) {
                return false;
            }
            this.from = vertices.get(0);
            this.to = vertices.get(1);
            return true;
        }

    }

    public void setVertexValue(int id, T value) {
        getVertexById(id).setValue(value);
    }

    private @NotNull Vertex getVertexById(int id) throws NoSuchElementException {
        for (Vertex v : vertices) {
            if (v.getId() == id) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    public void setEdgeWeight(int id, Double weight) {
        getEdgeById(id).setWeight(weight);
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
