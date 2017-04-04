package com.pw.eiti.graphisomorphism.checker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;

import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.pw.eiti.graphisomorphism.checker.DFSPreOrderBuilder;
import com.pw.eiti.graphisomorphism.model.Graph;

public class DFSPreOrderBuilderTest {

	private VertexSorter mockSorter;
	private DFSPreOrderBuilder dfsGraphBuilder;

	@Before
	public void setUp() {
		mockSorter = mock(VertexSorter.class);
		dfsGraphBuilder = new DFSPreOrderBuilder(mockSorter);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetDFSPreOrder() throws Exception {
		//given
		final Graph<Character> mockGraph = mock(Graph.class);
		when(mockSorter.getSortedVerticles(mockGraph)).thenReturn(Lists.newArrayList('a', 'b', 'c', 'd'));
		when(mockGraph.getNeighbours(eq('a'))).thenReturn(Sets.newHashSet('c'));
		when(mockGraph.getNeighbours(eq('b'))).thenReturn(Sets.newHashSet('d'));
		when(mockGraph.getNeighbours(eq('c'))).thenReturn(Collections.emptySet());
		when(mockGraph.getNeighbours(eq('d'))).thenReturn(Sets.newHashSet('a'));
		//when
		final Map<Character, Integer> dfsPreOrder = dfsGraphBuilder.getDFSPreOrder(mockGraph);
		//then
		assertThat(dfsPreOrder.entrySet())
		.extracting("key", "value").containsExactly(
				new Tuple('a', 0),
				new Tuple('c', 1),
				new Tuple('b', 2),
				new Tuple('d', 3));
	}
}