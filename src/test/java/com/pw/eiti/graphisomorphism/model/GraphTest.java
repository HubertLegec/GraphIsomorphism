package com.pw.eiti.graphisomorphism.model;

import java.util.Arrays;

import org.junit.Test;

import com.pw.eiti.graphisomorphism.ui.InvalidGraphException;

public class GraphTest {

	@Test
	public void graphWithNoEdges() {
		final Graph<String> g = new Graph<>();
		g.setVertices(Arrays.asList("a", "b", "c"));
		g.validate();
	}

	@Test
	public void validGraphWithEdges() {
		final Graph<String> g = new Graph<>();
		g.setVertices(Arrays.asList("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("b", "c"), new Edge<String>("c", "a")));
		g.validate();
	}

	@Test(expected = InvalidGraphException.class)
	public void graphWithInvalidEdges() {
		final Graph<String> g = new Graph<>();
		g.setVertices(Arrays.asList("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("b", "d"), new Edge<String>("e", "a")));
		g.validate();
	}

	@Test(expected = InvalidGraphException.class)
	public void graphWithDuplicatedEdges() {
		final Graph<String> g = new Graph<>();
		g.setVertices(Arrays.asList("a", "b", "c"));
		g.setEdges(Arrays.asList(new Edge<String>("a", "b"), new Edge<String>("a", "b"), new Edge<String>("c", "a")));
		g.validate();
	}

}
