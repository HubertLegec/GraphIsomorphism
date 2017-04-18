package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.pw.eiti.graphisomorphism.checker.dfs.DFSPreOrderBuilder;
import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;

public class VertexMatchRequirementsFactoryTest {

    private DFSPreOrderBuilder mockDfsPreOrderBuilder;
    private VertexMatchRequirementsFactory vertexMatcherBuilder;

    @Before
    public void setUp() {
        mockDfsPreOrderBuilder = mock(DFSPreOrderBuilder.class);
        vertexMatcherBuilder = new VertexMatchRequirementsFactory(mockDfsPreOrderBuilder);
    }

    private void assertRequirement(final VertexMatchRequirement req, final int vertex, final List<Edge> edges) {
        assertThat(req.getVertexToMatch()).isEqualTo(vertex);
        assertThat(req.getEdgesToMatch()).containsAll(edges);
    }

    @Test
    public void testGetRequirementsFor_edgeAssignedToVertexWithHigherPreOrder() {
        //given
        when(mockDfsPreOrderBuilder.getVertexToDFSPreOrder(any()))
                .thenReturn(ImmutableMap.of(
                        2, 0,
                        0, 1,
                        1, 2));
        final Graph graph = new Graph();
        graph.setVerticesCount(3);
        graph.setEdges(
                Arrays.asList(
                        new Edge(0, 1),
                        new Edge(0, 2),
                        new Edge(1, 2)
                ));
        //when
        final List<VertexMatchRequirement> requirements = vertexMatcherBuilder.getRequirementsFor(graph);
        //then
        assertRequirement(requirements.get(0), 2, Collections.emptyList());
        assertRequirement(requirements.get(1), 0,
                Collections.singletonList(new Edge(0, 2)));
        assertRequirement(requirements.get(2), 1,
                Arrays.asList(new Edge(0, 1), new Edge(1, 2)));
    }
}