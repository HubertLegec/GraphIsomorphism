package com.pw.eiti.graphisomorphism.checker.preconditions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.pw.eiti.graphisomorphism.model.Graph;

public class VerticesCountPreconditionTest {

	private VerticesCountPrecondition verticesCountPrecondition;

	@Before
	public void setUp() {
		verticesCountPrecondition = new VerticesCountPrecondition();
	}

	@Test
	public void testFullfils_success() throws Exception {
		// given
		final Graph<String> graphA = mock(Graph.class);
		final Graph<String> graphB = mock(Graph.class);
		when(graphA.getVerticesCount()).thenReturn(11);
		when(graphB.getVerticesCount()).thenReturn(11);
		// when
		final boolean fullfils = verticesCountPrecondition.fullfils(graphA, graphB);
		// then
		assertThat(fullfils).isTrue();
	}

	@Test
	public void testFullfils_failure() throws Exception {
		// given
		final Graph<String> graphA = mock(Graph.class);
		final Graph<String> graphB = mock(Graph.class);
		when(graphA.getVerticesCount()).thenReturn(11);
		when(graphB.getVerticesCount()).thenReturn(12);
		// when
		final boolean fullfils = verticesCountPrecondition.fullfils(graphA, graphB);
		// then
		assertThat(fullfils).isFalse();
	}
}
