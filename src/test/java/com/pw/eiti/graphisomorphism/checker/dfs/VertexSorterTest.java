package com.pw.eiti.graphisomorphism.checker.dfs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import com.pw.eiti.graphisomorphism.model.Edge;
import org.junit.Test;

import com.pw.eiti.graphisomorphism.model.Graph;

public class VertexSorterTest {

    @Test
    public void testGetSortedVertices() throws Exception {
        //given
        final Graph g = getGraph();
        //when
        final List<Integer> sortedVertices = VertexSorter.getSortedVertices(g);
        //then
        assertThat(sortedVertices).containsExactly(5, 3, 4, 0, 1, 2);
    }

    private Graph getGraph() {
        Graph g = new Graph();
        g.setVerticesCount(6);
        /**
         * Vertices degrees:
         * 0 - 2 out i 2 in
         * 1 - 2 out i 2 in
         * 2 - 2 out i 2 in
         * 3 - 4 out i 4 in
         * 4 - 4 out i 4 in
         * 5 - 3 out i 3 in
         */
        List<Edge> edges = Arrays.asList(
                new Edge(0, 4),
                new Edge(1, 4),
                new Edge(2, 4),
                new Edge(3, 4),
                new Edge(4, 0),
                new Edge(4, 1),
                new Edge(4, 2),
                new Edge(4, 3),
                new Edge(0, 3),
                new Edge(1, 3),
                new Edge(3, 0),
                new Edge(3, 1),
                new Edge(2, 5),
                new Edge(5, 3),
                new Edge(3,5),
                new Edge(5, 2),
                new Edge(5, 5)
        );
        g.setEdges(edges);
        return g;
    }
}