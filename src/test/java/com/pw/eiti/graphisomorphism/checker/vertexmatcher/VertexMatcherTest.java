package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;

public class VertexMatcherTest {

	@Before
	public void setUp() {
	}

	private void assertMatching(final Optional<VertexMatching> matching, final int vSrc, final int vDst) {
		assertThat(matching.get().getDstBySrc(vSrc)).isEqualTo(vDst);
	}

	@Test
	public void testGetMatchingTo_isolatedVertices_success() throws Exception {
		//given
		final VertexMatcher matcher = new VertexMatcher(Lists.newArrayList(
				new VertexMatchRequirement(0, Collections.emptyList()),
				new VertexMatchRequirement(1, Collections.emptyList())
				));
		final Graph dstGraph = new Graph();
		dstGraph.setVerticesCount(2);
		//when
		final Optional<VertexMatching> matching = matcher.getMatchingTo(dstGraph);
		//then
		assertThat(matching.isPresent()).isTrue();
		assertMatching(matching, 0, 0);
		assertMatching(matching, 1, 1);
	}

	@Test
	public void testGetMatchingTo_oneEdge_success() throws Exception {
		//given
		final VertexMatcher matcher = new VertexMatcher(Lists.newArrayList(
				new VertexMatchRequirement(0, Collections.emptyList()),
				new VertexMatchRequirement(1, Collections.singletonList(new Edge(0, 1)))
				));
		final Graph dstGraph = new Graph();
		dstGraph.setVerticesCount(2);
		dstGraph.setEdges(Collections.singletonList(new Edge(1, 0)));
		//when
		final Optional<VertexMatching> matching = matcher.getMatchingTo(dstGraph);
		//then
		assertThat(matching.isPresent()).isTrue();
		assertMatching(matching, 0, 1);
		assertMatching(matching, 1, 0);
	}

	@Test
	public void testGetMatchingTo_twoEdges_sameDirection_success() throws Exception {
		//given
		final VertexMatcher matcher = new VertexMatcher(Lists.newArrayList(
				new VertexMatchRequirement(0, Collections.emptyList()),
				new VertexMatchRequirement(1, Collections.emptyList()),
				new VertexMatchRequirement(2, Lists.newArrayList(new Edge(2, 0), new Edge(2, 1)))
				));
		final Graph dstGraph = new Graph();
		dstGraph.setVerticesCount(3);
		dstGraph.setEdges(Lists.newArrayList(new Edge(0, 1), new Edge(0, 2)));
		//when
		final Optional<VertexMatching> matching = matcher.getMatchingTo(dstGraph);
		//then
		assertThat(matching.isPresent()).isTrue();
		assertMatching(matching, 0, 1);
		assertMatching(matching, 1, 2);
		assertMatching(matching, 2, 0);
	}

	@Test
	public void testGetMatchingTo_twoEdges_badDirection_failure() throws Exception {
		//given
		final VertexMatcher matcher = new VertexMatcher(Lists.newArrayList(
				new VertexMatchRequirement(0, Collections.emptyList()),
				new VertexMatchRequirement(1, Collections.emptyList()),
				new VertexMatchRequirement(2, Lists.newArrayList(new Edge(2, 0), new Edge(2, 1)))
				));
		final Graph dstGraph = new Graph();
		dstGraph.setVerticesCount(3);
		dstGraph.setEdges(Lists.newArrayList(new Edge(2, 0), new Edge(1, 2)));
		//when
		final Optional<VertexMatching> matching = matcher.getMatchingTo(dstGraph);
		//then
		assertThat(matching.isPresent()).isFalse();
	}
}