package com.pw.eiti.graphisomorphism.checker.preconditions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.pw.eiti.graphisomorphism.checker.DegreeCalculator;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

public class DegreePreconditionTest {

	private DegreeCalculator mockDegreCalc;
	private DegreePrecondition degreePrecondition;

	@Before
	public void setUp() {
		mockDegreCalc = mock(DegreeCalculator.class);
		degreePrecondition = new DegreePrecondition(mockDegreCalc);
	}

	@Test
	public void testFullfils_success() throws Exception {
		// given
		final Graph<String> graphA = mock(Graph.class);
		final Graph<String> graphB = mock(Graph.class);
		final Map<String, VertexDegree> graphADegrees = ImmutableMap.of(
				"a", getMockVertexDegree(1, 2),
				"b", getMockVertexDegree(5, 10),
				"c", getMockVertexDegree(5, 10),
				"d", getMockVertexDegree(100, 100));
		final Map<String, VertexDegree> graphBDegrees = ImmutableMap.of(
				"z", getMockVertexDegree(1, 2),
				"x", getMockVertexDegree(5, 10),
				"f", getMockVertexDegree(5, 10),
				"g", getMockVertexDegree(100, 100));
		when(mockDegreCalc.getDegrees(graphA)).thenReturn(graphADegrees);
		when(mockDegreCalc.getDegrees(graphB)).thenReturn(graphBDegrees);
		// when
		final boolean fullfiled = degreePrecondition.fullfils(graphA, graphA);
		// then
		assertThat(fullfiled).isTrue();
	}

	@Test
	public void testFullfils_failure() throws Exception {
		// given
		final Graph<String> graphA = mock(Graph.class);
		final Graph<String> graphB = mock(Graph.class);
		final Map<String, VertexDegree> graphADegrees = ImmutableMap.of(
				"a", getMockVertexDegree(1, 2),
				"b", getMockVertexDegree(5, 10),
				"c", getMockVertexDegree(5, 10),
				"d", getMockVertexDegree(100, 100));
		final Map<String, VertexDegree> graphBDegrees = ImmutableMap.of(
				"z", getMockVertexDegree(1, 2),
				"x", getMockVertexDegree(5, 10),
				"f", getMockVertexDegree(5, 10),
				"g", getMockVertexDegree(5, 10));
		when(mockDegreCalc.getDegrees(graphA)).thenReturn(graphADegrees);
		when(mockDegreCalc.getDegrees(graphB)).thenReturn(graphBDegrees);
		// when
		final boolean fullfiled = degreePrecondition.fullfils(graphA, graphB);
		// then
		assertThat(fullfiled).isFalse();
	}

	private VertexDegree getMockVertexDegree(final Integer in, final Integer out) {
		final VertexDegree mockDegree = mock(VertexDegree.class);
		when(mockDegree.getInDeg()).thenReturn(in);
		when(mockDegree.getOutDeg()).thenReturn(out);
		return mockDegree;
	}

}
