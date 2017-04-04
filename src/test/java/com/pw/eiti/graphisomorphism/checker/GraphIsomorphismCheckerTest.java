package com.pw.eiti.graphisomorphism.checker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.pw.eiti.graphisomorphism.checker.preconditions.Precondition;

public class GraphIsomorphismCheckerTest {

	private Precondition mockPrecondition;
	private GraphIsomorphismChecker checker;

	@Before
	public void setUp() {
		mockPrecondition = mock(Precondition.class);
		when(mockPrecondition.fullfils(any(), any())).thenReturn(true);
		final DFSPreOrderBuilder mockOrderedGraphBuilder = mock(DFSPreOrderBuilder.class);
		checker = new GraphIsomorphismChecker(mockPrecondition, mockOrderedGraphBuilder);
	}

	@Test
	public void testGetIsomorhismGraphGraph_checksPreconditions() throws Exception {
		//given
		when(mockPrecondition.fullfils(any(), any())).thenReturn(false);
		//when
		final GraphIsomorphismDefinition<String> isomorhism = checker.getIsomorhism(null, null);
		//then
		assertThat(isomorhism).isNull();
	}
}