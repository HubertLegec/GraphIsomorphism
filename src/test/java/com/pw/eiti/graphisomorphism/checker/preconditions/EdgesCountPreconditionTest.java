package com.pw.eiti.graphisomorphism.checker.preconditions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;

public class EdgesCountPreconditionTest {

	private EdgesCountPrecondition edgesCountPrecondition;

	@Before
	public void setUp() {
		edgesCountPrecondition = new EdgesCountPrecondition();
	}

	@Test
	public void testFullfils_success() throws Exception {
		// given
		final Graph graphA = mock(Graph.class);
		final Graph graphB = mock(Graph.class);
		when(graphA.getEdges()).thenReturn(Lists.newArrayList(new Edge(1, 2)));
		when(graphB.getEdges()).thenReturn(Lists.newArrayList(new Edge(7, 5)));
		// when
		final boolean fullfils = edgesCountPrecondition.fullfils(graphA, graphB);
		// then
		assertThat(fullfils).isTrue();
	}

	@Test
	public void testFullfils_failure() throws Exception {
		// given
		final Graph graphA = mock(Graph.class);
		final Graph graphB = mock(Graph.class);
		when(graphA.getEdges()).thenReturn(Lists.newArrayList(new Edge(1, 2), new Edge(4,  3)));
		when(graphB.getEdges()).thenReturn(Lists.newArrayList(new Edge(7, 5)));
		// when
		final boolean fullfils = edgesCountPrecondition.fullfils(graphA, graphB);
		// then
		assertThat(fullfils).isFalse();
	}

}
