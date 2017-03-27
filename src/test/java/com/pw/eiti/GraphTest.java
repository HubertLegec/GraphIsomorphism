package com.pw.eiti;

import org.junit.Test;

import java.util.Arrays;

public class GraphTest {

    @Test
    public void graphWithNoEdges() {
        Graph g = new Graph();
        g.setVerticesCount(3);
        g.setVertices(Arrays.asList("a", "b", "c"));
        g.validate();
    }

    @Test(expected = InvalidGraphException.class)
    public void numberOfVerticesNotDeclared() {
        Graph g = new Graph();
        g.setVertices(Arrays.asList("a", "b", "c"));
        g.validate();
    }

    @Test
    public void validGraphWithEdges() {
        Graph g = new Graph();
        g.setVerticesCount(3);
        g.setVertices(Arrays.asList("a", "b", "c"));
        g.setEdgesCount(3);
        g.setEdges(Arrays.asList(new Edge("a", "b"), new Edge("b", "c"), new Edge("c", "a")));
        g.validate();
    }

    @Test(expected = InvalidGraphException.class)
    public void graphWithInvalidEdges() {
        Graph g = new Graph();
        g.setVerticesCount(3);
        g.setVertices(Arrays.asList("a", "b", "c"));
        g.setEdgesCount(3);
        g.setEdges(Arrays.asList(new Edge("a", "b"), new Edge("b", "d"), new Edge("e", "a")));
        g.validate();
    }

    @Test(expected = InvalidGraphException.class)
    public void graphWithDuplicatedEdges() {
        Graph g = new Graph();
        g.setVerticesCount(3);
        g.setVertices(Arrays.asList("a", "b", "c"));
        g.setEdgesCount(3);
        g.setEdges(Arrays.asList(new Edge("a", "b"), new Edge("a", "b"), new Edge("c", "a")));
        g.validate();
    }

}
