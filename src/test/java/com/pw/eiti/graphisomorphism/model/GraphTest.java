package com.pw.eiti.graphisomorphism.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testGetEdgesCount() throws Exception {
		final Graph g = new Graph();
		g.setVerticesCount(3);
		g.setEdges(Arrays.asList(new Edge(0, 1), new Edge(0, 2), new Edge(2, 0)));
		assertThat(g.getEdges().size()).isEqualTo(3);
	}

	@Test
	public void testGetVerticesCount() throws Exception {
		final Graph g = new Graph();
		g.setVerticesCount(3);
		assertThat(g.getVerticesCount()).isEqualTo(3);
	}

	@Test
	public void testGetNeighbours_noNeighbours() throws Exception {
		final Graph g = new Graph();
		g.setVerticesCount(3);
		g.setEdges(Arrays.asList(new Edge(0, 1), new Edge(0, 2), new Edge(2, 0)));
		assertThat(g.getNeighbours(1)).isEmpty();
	}

	@Test
	public void testGetNeighbours_vertexDoesntExist() throws Exception {
		final Graph g = new Graph();
		g.setVerticesCount(3);
		g.setEdges(Arrays.asList(new Edge(0, 1), new Edge(0, 2), new Edge(2, 0)));
		assertThat(g.getNeighbours(99)).isEmpty();
	}

	@Test
	public void testGetNeighbours() throws Exception {
		final Graph g = new Graph();
		g.setVerticesCount(3);
		g.setEdges(Arrays.asList(new Edge(0, 1), new Edge(0, 2), new Edge(2, 0)));
		assertThat(g.getNeighbours(0)).contains(1, 2);
	}
}