package com.pw.eiti;

import org.apache.commons.collections.ListUtils;

import java.util.*;

public class Graph {
    private String name;
    private int verticesCount;
    private int edgesCount;
    private List<String> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public int getVerticesCount() {
        return verticesCount;
    }

    public void setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;
    }

    public int getEdgesCount() {
        return edgesCount;
    }

    public void setEdgesCount(int edgesCount) {
        this.edgesCount = edgesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getVertices() {
        return vertices;
    }

    public void setVertices(List<String> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void validate() {
        checkVertices();
        checkEdges();
    }

    private void checkVertices() {
        if (vertices.isEmpty()) {
            throw new InvalidGraphException("Graph is empty!");
        }
        if (verticesCount != vertices.size()) {
            throw new InvalidGraphException("Number of vertices is different from declared");
        }
    }

    private void checkEdges() {
        if (edgesCount != edges.size()) {
            throw new InvalidGraphException("Number of edges is different from declared");
        }
        if (edges.size() != edges.parallelStream().distinct().count()) {
            throw new InvalidGraphException("Graph has duplicated edges");
        }
        List<String> verticesFromEdges = edges.stream()
                .map(e -> Arrays.asList(e.getV1(), e.getV2()))
                .reduce(new ArrayList<>(), ListUtils::union);
        List diff = ListUtils.removeAll(verticesFromEdges, vertices);
        if (!diff.isEmpty()) {
            throw new InvalidGraphException("Graph has edges with unknown vertices");
        }
    }
}
