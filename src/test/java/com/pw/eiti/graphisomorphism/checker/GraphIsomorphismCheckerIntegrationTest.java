package com.pw.eiti.graphisomorphism.checker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.pw.eiti.graphisomorphism.checker.dfs.DFSPreOrderBuilder;
import com.pw.eiti.graphisomorphism.checker.preconditions.DegreePrecondition;
import com.pw.eiti.graphisomorphism.checker.preconditions.EdgesCountPrecondition;
import com.pw.eiti.graphisomorphism.checker.preconditions.GraphIsomorphismPreconditionCollection;
import com.pw.eiti.graphisomorphism.checker.preconditions.Precondition;
import com.pw.eiti.graphisomorphism.checker.preconditions.VerticesCountPrecondition;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatchRequirementsFactory;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatcherFactory;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatching;
import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;

public class GraphIsomorphismCheckerIntegrationTest {
    private GraphIsomorphismChecker checker;

    @Before
    public void setUp() {
        final Precondition precondition = new GraphIsomorphismPreconditionCollection(
                Arrays.asList(
                        new DegreePrecondition(),
                        new EdgesCountPrecondition(),
                        new VerticesCountPrecondition()
                ));
        final DFSPreOrderBuilder dfsPreOrderBuilder = new DFSPreOrderBuilder();
        final VertexMatchRequirementsFactory requirementsFactory =
                new VertexMatchRequirementsFactory(dfsPreOrderBuilder);
        final VertexMatcherFactory vertexMatcherFactory = new VertexMatcherFactory(requirementsFactory);
        checker = new GraphIsomorphismChecker(precondition, vertexMatcherFactory);
    }

    private Graph getGraph(final int vCount, final Multimap<Integer, Integer> edges) {
        final Graph graph = new Graph();
        graph.setVerticesCount(vCount);
        graph.setEdges(edges.entries().stream()
                .map(e -> new Edge(e.getKey(), e.getValue()))
                .collect(Collectors.toList()));
        return graph;
    }

    @Test
    public void testGetIsomorphism_trivialGraph_success() {
        //given
        final Graph second = getGraph(2, ImmutableMultimap.of());
        final Graph first = getGraph(2, ImmutableMultimap.of());
        //when
        final Optional<VertexMatching> isomorphism = checker.getIsomorphism(first, second);
        //then
        assertThat(isomorphism.isPresent()).isTrue();
        final Map<Integer, Integer> result = isomorphism.get().getMatching();
        final Map<Integer, Integer> expectedResult = ImmutableMap.of(
                0, 0,
                1, 1);
        assertThat(result.entrySet())
                .containsAll(expectedResult.entrySet());
    }

    @Test
    public void testGetIsomorphism_vertexCountNotEqual_failure() {
        //given
        final Graph second = getGraph(1, ImmutableMultimap.of());
        final Graph first = getGraph(2, ImmutableMultimap.of());
        //when
        final Optional<VertexMatching> isomorphism = checker.getIsomorphism(first, second);
        //then
        assertThat(isomorphism.isPresent()).isFalse();
    }

    @Test
    public void testGetIsomorphism_edgesCountNotEqual_failure() {
        //given
        final Graph second = getGraph(2, ImmutableMultimap.of());
        final Graph first = getGraph(2, ImmutableMultimap.of(0, 1));
        //when
        final Optional<VertexMatching> isomorphism = checker.getIsomorphism(first, second);
        //then
        assertThat(isomorphism.isPresent()).isFalse();
    }

    @Test
    public void testGetIsomorphism_oneEdge_success() {
        //given
        final Graph second = getGraph(2, ImmutableMultimap.of(1, 0));
        final Graph first = getGraph(2, ImmutableMultimap.of(0, 1));
        //when
        final Optional<VertexMatching> isomorphism = checker.getIsomorphism(first, second);
        //then
        assertThat(isomorphism.isPresent()).isTrue();
        final Map<Integer, Integer> result = isomorphism.get().getMatching();
        final Map<Integer, Integer> expectedResult = ImmutableMap.of(
                0, 1,
                1, 0);
        assertThat(result.entrySet()).containsAll(expectedResult.entrySet());
    }

    @Test
    public void testGetIsomorphism_treeWithThreeVertices_success() {
        //given
        final Graph second = getGraph(3, ImmutableMultimap.of(0, 1, 0, 2));
        final Graph first = getGraph(3, ImmutableMultimap.of(2, 0, 2, 1));
        //when
        final Optional<VertexMatching> isomorphism = checker.getIsomorphism(first, second);
        //then
        assertThat(isomorphism.isPresent()).isTrue();
        final Map<Integer, Integer> result = isomorphism.get().getMatching();
        final Map<Integer, Integer> expectedResult = ImmutableMap.of(
                0, 2,
                1, 1,
                2, 0);
        assertThat(result.entrySet()).containsAll(expectedResult.entrySet());
    }

    @Test
    public void testGetIsomorphism_treeWithThreeVertices_degreeDistributionWrong_failure() {
        //given
        final Graph second = getGraph(3, ImmutableMultimap.of(1, 0, 2, 0));
        final Graph first = getGraph(3, ImmutableMultimap.of(0, 1, 0, 2));
        //when
        final Optional<VertexMatching> isomorphism = checker.getIsomorphism(first, second);
        //then
        assertThat(isomorphism.isPresent()).isFalse();
    }

    @Test
    public void testGetIsomorphism_cycleWithChord_success() {
        //given
        final Graph second = getGraph(4, ImmutableMultimap.of(
                0, 1, 1, 2, 2, 3, 3, 0, 1, 0));
        final Graph first = getGraph(4, ImmutableMultimap.of(
                0, 3, 3, 2, 2, 1, 1, 0, 3, 0));
        //when
        final Optional<VertexMatching> isomorphism = checker.getIsomorphism(first, second);
        //then
        assertThat(isomorphism.isPresent()).isTrue();
        assertThat(isomorphism.get().getMatching().entrySet()).containsAll(ImmutableMultimap.of(
                0, 0,
                1, 3,
                2, 2,
                3, 1).entries());
    }

    @Test
    public void testGetIsomorphism_cycleWithChord_differentChord_failure() {
        //given
        final Graph second = getGraph(4, ImmutableMultimap.of(
                0, 1, 1, 2, 2, 3, 3, 0, 1, 0));
        final Graph first = getGraph(4, ImmutableMultimap.of(
                0, 3, 3, 2, 2, 1, 1, 0, 2, 0));
        //when
        final Optional<VertexMatching> isomorphism = checker.getIsomorphism(first, second);
        //then
        assertThat(isomorphism.isPresent()).isFalse();
    }
}