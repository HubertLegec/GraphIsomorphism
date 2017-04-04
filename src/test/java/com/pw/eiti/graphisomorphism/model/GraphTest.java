package com.pw.eiti.graphisomorphism.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.google.common.collect.Sets;
import com.pw.eiti.graphisomorphism.ui.InvalidGraphException;

public class GraphTest {

	@Test
	public void graphWithNoEdges() {
		final Graph<String> g = new Graph<>();
		g.setVertices(Sets.newHashSet("a", "b", "c"));
		g.validate();
	}

	@Test
	public void validGraphWithEdges() {
		final Graph<String> g = new Graph<>();
		g.setVertices(Sets.newHashSet("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("b", "c"), new Edge<String>("c", "a")));
		g.validate();
	}

	@Test(expected = InvalidGraphException.class)
	public void graphWithInvalidEdges() {
		final Graph<String> g = new Graph<>();
		g.setVertices(Sets.newHashSet("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("b", "d"), new Edge<String>("e", "a")));
		g.validate();
	}

	@Test
	public void graphWithDuplicatedEdges_removesDuplicateEdges() {
		final Graph<String> g = new Graph<>();
		g.setVertices(Sets.newHashSet("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("a", "b"), new Edge<String>("c", "a")));
		assertThat(g.getEdges().size()).isEqualTo(2);
	}

	@Test
	public void testGetEdgesCount() throws Exception {
		final Graph<String> g = new Graph<>();
		g.setVertices(Sets.newHashSet("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("a", "c"), new Edge<String>("c", "a")));
		assertThat(g.getEdgesCount()).isEqualTo(3);
	}

	@Test
	public void testGetVerticesCount() throws Exception {
		final Graph<String> g = new Graph<>();
		g.setVertices(Sets.newHashSet("a", "b", "c"));
		assertThat(g.getVerticesCount()).isEqualTo(3);
	}

	@Test
	public void testGetNeighbours_noNeighbours() throws Exception {
		final Graph<String> g = new Graph<>();
		g.setVertices(Sets.newHashSet("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("a", "c"), new Edge<String>("c", "a")));
		assertThat(g.getNeighbours("b")).isEmpty();
	}

	@Test
	public void testGetNeighbours_vertexDoesntExist() throws Exception {
		final Graph<String> g = new Graph<>();
		g.setVertices(Sets.newHashSet("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("a", "c"), new Edge<String>("c", "a")));
		assertThat(g.getNeighbours("x")).isEmpty();
	}

	@Test
	public void testGetNeighbours() throws Exception {
		final Graph<String> g = new Graph<>();
		g.setVertices(Sets.newHashSet("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("a", "c"), new Edge<String>("c", "a")));
		assertThat(g.getNeighbours("a")).contains("b", "c");
	}
}