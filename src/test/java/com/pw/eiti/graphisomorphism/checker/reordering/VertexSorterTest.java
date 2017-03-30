package com.pw.eiti.graphisomorphism.checker.reordering;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.pw.eiti.graphisomorphism.checker.DegreeCalculator;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

public class VertexSorterTest {

	private DegreeCalculator mockDegreeCalc;
	private VertexSorter sorter;

	@Before
	public void setUp() {
		mockDegreeCalc = mock(DegreeCalculator.class);
		sorter = new VertexSorter(mockDegreeCalc);
	}

	@Test
	public void testSortVerticles() throws Exception {
		//given
		final Graph<String> g = mock(Graph.class);
		final List<String> vertcies = Arrays.asList("a", "b", "c", "d", "e", "f");
		when(g.getVertices()).thenReturn(vertcies);
		final Map<String, VertexDegree> degreeMap = Maps.newHashMap();
		degreeMap.put("f", new VertexDegree(10, 10)); //nie mo�na dopasowa� - 0
		degreeMap.put("e", new VertexDegree(5, 5)); //mo�na dopasowa� z d - 1
		degreeMap.put("d", new VertexDegree(5, 5)); //mo�na dopasowa� z e - 1
		degreeMap.put("c", new VertexDegree(1, 2)); //mo�na dopasowa� z b i a - 2
		degreeMap.put("b", new VertexDegree(1, 2)); //mo�na dopasowa� z a i c - 2
		degreeMap.put("a", new VertexDegree(1, 2)); //mo�na dopasowa� z b i c - 2
		when(mockDegreeCalc.getDegrees(g)).thenReturn(degreeMap);
		//when
		sorter.sortVerticles(g);
		//then
		assertThat(vertcies).containsExactly("f", "d", "e", "a", "b", "c");
	}
}