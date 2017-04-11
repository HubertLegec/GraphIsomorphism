package com.pw.eiti.graphisomorphism.checker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

public class DegreeCalculatorTest {

	private DegreeCalculator degreeCalc;

	@Before
	public void setUp() {
		degreeCalc = new DegreeCalculator();
	}

	@Test
	public void testGetDegrees() throws Exception {
		//given
		final Graph mockGraph = mock(Graph.class);
		final List<Edge> mockEdges = Arrays.asList(
				getMockEdge(0, 1),
				getMockEdge(0, 2),
				getMockEdge(0, 3),
				getMockEdge(1, 2),
				getMockEdge(1, 3)
				);
		when(mockGraph.getEdges()).thenReturn(mockEdges);
		//when
		final Map<Integer, VertexDegree> degrees = degreeCalc.getDegrees(mockGraph);
		//then
		assertThat(degrees.keySet().size()).isEqualTo(4);
		assertThat(degrees.get(0)).isEqualTo(new VertexDegree(0, 3));
		assertThat(degrees.get(1)).isEqualTo(new VertexDegree(1, 2));
		assertThat(degrees.get(2)).isEqualTo(new VertexDegree(2, 0));
		assertThat(degrees.get(3)).isEqualTo(new VertexDegree(2, 0));
	}

	private Edge getMockEdge(final Integer start, final Integer end) {
		final Edge mockEdge = mock(Edge.class);
		when(mockEdge.getV1()).thenReturn(start);
		when(mockEdge.getV2()).thenReturn(end);
		return mockEdge;
	}
}
