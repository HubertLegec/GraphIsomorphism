package com.pw.eiti.graphisomorphism.checker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.pw.eiti.graphisomorphism.checker.preconditions.Precondition;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatcher;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatcherFactory;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatching;
import com.pw.eiti.graphisomorphism.model.Graph;

public class GraphIsomorphismCheckerTest {

	private Graph mockSrcGraph;
	private Graph mockDstGraph;
	private Precondition mockPrecondition;
	private GraphIsomorphismChecker checker;
	private VertexMatcherFactory mockMatcherFactory;
	private VertexMatcher mockMatcher;
	private VertexMatching mockMatching;

	@Before
	public void setUp() {
		mockSrcGraph = mock(Graph.class);
		mockDstGraph = mock(Graph.class);
		mockPrecondition = mock(Precondition.class);
		mockMatcherFactory = mock(VertexMatcherFactory.class);
		mockMatcher = mock(VertexMatcher.class);
		mockMatching = mock(VertexMatching.class);
		checker = new GraphIsomorphismChecker(mockPrecondition, mockMatcherFactory);

		setUpSuccessScenario();
	}

	private void setUpSuccessScenario() {
		when(mockPrecondition.fulfills(mockSrcGraph, mockDstGraph)).thenReturn(true);
		when(mockMatcherFactory.getVertexMatcher(mockSrcGraph)).thenReturn(mockMatcher);
		when(mockMatcher.getMatchingTo(mockDstGraph)).thenReturn(Optional.of(mockMatching));
	}

	@Test
	public void testGetIsomorhismGraphGraph_successScenario_returnsMatchingFromMatcher() throws Exception {
		//given when
		final Optional<VertexMatching> optionalMatching = checker.getIsomorphism(mockSrcGraph, mockDstGraph);
		//then
		assertThat(optionalMatching.isPresent()).isTrue();
		assertThat(optionalMatching.get()).isEqualTo(mockMatching);
	}

	@Test
	public void testGetIsomorhismGraphGraph_checksPreconditions() throws Exception {
		//given
		when(mockPrecondition.fulfills(mockSrcGraph, mockDstGraph)).thenReturn(false);
		//when
		final Optional<VertexMatching> vertexMatching = checker.getIsomorphism(mockSrcGraph, mockDstGraph);
		//then
		assertThat(vertexMatching.isPresent()).isFalse();
	}
}