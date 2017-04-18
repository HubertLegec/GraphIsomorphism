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
	public void testFulfills_success() {
		// given
		final Graph graphA = mock(Graph.class);
		final Graph graphB = mock(Graph.class);
		when(graphA.getVerticesCount()).thenReturn(11);
		when(graphB.getVerticesCount()).thenReturn(11);
		// when
		final boolean fulfills = verticesCountPrecondition.fulfills(graphA, graphB);
		// then
		assertThat(fulfills).isTrue();
	}

	@Test
	public void testFulfills_failure() {
		// given
		final Graph graphA = mock(Graph.class);
		final Graph graphB = mock(Graph.class);
		when(graphA.getVerticesCount()).thenReturn(11);
		when(graphB.getVerticesCount()).thenReturn(12);
		// when
		final boolean fulfills = verticesCountPrecondition.fulfills(graphA, graphB);
		// then
		assertThat(fulfills).isFalse();
	}
}
