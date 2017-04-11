package com.pw.eiti.graphisomorphism.checker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.pw.eiti.graphisomorphism.model.Graph;

public class DFSPreOrderBuilderTest {

	private VertexSorter mockSorter;
	private DFSPreOrderBuilder dfsGraphBuilder;

	@Before
	public void setUp() {
		mockSorter = mock(VertexSorter.class);
		dfsGraphBuilder = new DFSPreOrderBuilder(mockSorter);
	}

	private Graph setUpTestCase() {
		//given
		final Graph mockGraph = mock(Graph.class);
		when(mockSorter.getSortedVerticles(mockGraph)).thenReturn(Lists.newArrayList(0, 1, 2, 3));
		when(mockGraph.getVertices()).thenReturn(Lists.newArrayList(0, 1, 2, 3));
		when(mockGraph.getNeighbours(eq(0))).thenReturn(Sets.newHashSet(2));
		when(mockGraph.getNeighbours(eq(1))).thenReturn(Sets.newHashSet(3));
		when(mockGraph.getNeighbours(eq(2))).thenReturn(Collections.emptySet());
		when(mockGraph.getNeighbours(eq(3))).thenReturn(Sets.newHashSet(0));
		return mockGraph;
	}

	@Test
	public void testGetDFSPreOrder() throws Exception {
		//given
		final Graph mockGraph = setUpTestCase();
		//when
		final Map<Integer, Integer> dfsPreOrder = dfsGraphBuilder.getDFSPreOrder(mockGraph);
		//then
		assertThat(dfsPreOrder.entrySet())
		.extracting("key", "value").containsOnly(
				new Tuple(0, 0),
				new Tuple(2, 1),
				new Tuple(1, 2),
				new Tuple(3, 3));
	}

	@Test
	public void testGetVerticesSortedByPreOrder() throws Exception {
		//given
		final Graph mockGraph = setUpTestCase();
		//when
		final List<Integer> verticesSortedByPreOrder = dfsGraphBuilder.getVerticesSortedByPreOrder(mockGraph);
		assertThat(verticesSortedByPreOrder)
		.containsExactly(0, 2, 1, 3);
	}
}