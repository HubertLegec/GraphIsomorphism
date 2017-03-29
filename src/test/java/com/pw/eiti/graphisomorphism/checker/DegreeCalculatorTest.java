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
		final Graph<String> mockGraph = mock(Graph.class);
		final List<Edge<String>> mockEdges = Arrays.asList(
				getMockEdge("a", "b"),
				getMockEdge("a", "c"),
				getMockEdge("a", "d"),
				getMockEdge("b", "c"),
				getMockEdge("b", "d")
				);
		when(mockGraph.getEdges()).thenReturn(mockEdges);
		//when
		final Map<String, VertexDegree> degrees = degreeCalc.getDegrees(mockGraph);
		//then
		assertThat(degrees.keySet().size()).isEqualTo(4);
		assertThat(degrees.get("a")).isEqualTo(new VertexDegree(0, 3));
		assertThat(degrees.get("b")).isEqualTo(new VertexDegree(1, 2));
		assertThat(degrees.get("c")).isEqualTo(new VertexDegree(2, 0));
		assertThat(degrees.get("d")).isEqualTo(new VertexDegree(2, 0));
	}

	private Edge<String> getMockEdge(final String start, final String end) {
		final Edge<String> mockEdge = mock(Edge.class);
		when(mockEdge.getV1()).thenReturn(start);
		when(mockEdge.getV2()).thenReturn(end);
		return mockEdge;
	}
}
