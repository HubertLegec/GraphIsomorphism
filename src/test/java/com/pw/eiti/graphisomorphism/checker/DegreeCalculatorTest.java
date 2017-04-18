package com.pw.eiti.graphisomorphism.checker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

public class DegreeCalculatorTest {

    @Test
    public void testGetDegrees() throws Exception {
        //given
        final Graph graph = new Graph();
        final List<Edge> edges = Arrays.asList(
                new Edge(0, 1),
                new Edge(0, 2),
                new Edge(0, 3),
                new Edge(1, 2),
                new Edge(1, 3)
        );
        graph.setVerticesCount(4);
        graph.setEdges(edges);
        //when
        final Map<Integer, VertexDegree> degrees = DegreeCalculator.getDegrees(graph);
        //then
        assertThat(degrees.keySet().size()).isEqualTo(4);
        assertThat(degrees.get(0)).isEqualTo(new VertexDegree(0, 3));
        assertThat(degrees.get(1)).isEqualTo(new VertexDegree(1, 2));
        assertThat(degrees.get(2)).isEqualTo(new VertexDegree(2, 0));
        assertThat(degrees.get(3)).isEqualTo(new VertexDegree(2, 0));
    }
}
