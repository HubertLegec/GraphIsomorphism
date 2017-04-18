package com.pw.eiti.graphisomorphism.checker.preconditions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.pw.eiti.graphisomorphism.model.Edge;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.pw.eiti.graphisomorphism.checker.DegreeCalculator;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;
import sun.security.provider.certpath.Vertex;

public class DegreePreconditionTest {
	private DegreePrecondition degreePrecondition;

	@Before
	public void setUp() {
		degreePrecondition = new DegreePrecondition();
	}

	@Test
	public void testFulfills_success() {
		// given
		final Graph graphA = getGraphA();
		final Graph graphB = getGraphB();
		// when
		final boolean fulfilled = degreePrecondition.fulfills(graphA, graphB);
		// then
		assertThat(fulfilled).isTrue();
	}

	@Test
	public void testFulfills_failure() {
		// given
		final Graph graphA = getGraphA();
		final Graph graphC = getGraphC();
		// when
		final boolean fulfilled = degreePrecondition.fulfills(graphA, graphC);
		// then
		assertThat(fulfilled).isFalse();
	}

	private static Graph getGraphA() {
		Graph g = new Graph();
		g.setVerticesCount(4);
		List<Edge> edges = Arrays.asList(
				new Edge(0, 1),
				new Edge(1, 2),
				new Edge(2, 3),
				new Edge(3, 1)
		);
		g.setEdges(edges);
		return g;
	}

	private static Graph getGraphB() {
		Graph g = new Graph();
		g.setVerticesCount(4);
		List<Edge> edges = Arrays.asList(
				new Edge(0, 1),
				new Edge(1, 2),
				new Edge(2, 3),
				new Edge(3, 2)
		);
		g.setEdges(edges);
		return g;
	}

	private static Graph getGraphC() {
		Graph g = new Graph();
		g.setVerticesCount(4);
		List<Edge> edges = Arrays.asList(
				new Edge(0, 1),
				new Edge(1, 2),
				new Edge(2, 3),
				new Edge(3, 0)
		);
		g.setEdges(edges);
		return g;
	}

}
