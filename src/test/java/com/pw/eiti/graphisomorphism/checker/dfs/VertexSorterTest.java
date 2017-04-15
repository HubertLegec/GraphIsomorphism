package com.pw.eiti.graphisomorphism.checker.dfs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
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
	public void testGetSortedVerticles() throws Exception {
		//given
		final Graph g = mock(Graph.class);
		final List<Integer> vertcies = Lists.newArrayList(0, 1, 2, 3, 4, 5);
		when(g.getVertices()).thenReturn(vertcies);
		final Map<Integer, VertexDegree> degreeMap = Maps.newHashMap();
		degreeMap.put(5, new VertexDegree(10, 10)); //nie mo�na dopasowa� - 0
		degreeMap.put(4, new VertexDegree(5, 5)); //mo�na dopasowa� z d - 1
		degreeMap.put(3, new VertexDegree(5, 5)); //mo�na dopasowa� z e - 1
		degreeMap.put(2, new VertexDegree(1, 2)); //mo�na dopasowa� z b i a - 2
		degreeMap.put(1, new VertexDegree(1, 2)); //mo�na dopasowa� z a i c - 2
		degreeMap.put(0, new VertexDegree(1, 2)); //mo�na dopasowa� z b i c - 2
		when(mockDegreeCalc.getDegrees(g)).thenReturn(degreeMap);
		//when
		final List<Integer> sortedVerticles = sorter.getSortedVertices(g);
		//then
		assertThat(sortedVerticles).containsExactly(5, 3, 4, 0, 1, 2);
	}
}