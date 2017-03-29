package com.pw.eiti.graphisomorphism.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;

import com.pw.eiti.graphisomorphism.ui.InvalidGraphException;

public class Graph<V> {
	private String name;
	private List<V> vertices = new ArrayList<>();
	private List<Edge<V>> edges = new ArrayList<>();

	public int getVerticesCount() {
		return vertices.size();
	}

	public int getEdgesCount() {
		return edges.size();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<V> getVertices() {
		return vertices;
	}

	public void setVertices(final List<V> vertices) {
		this.vertices = vertices;
	}

	public List<Edge<V>> getEdges() {
		return edges;
	}

	public void setEdges(final List<Edge<V>> edges) {
		this.edges = edges;
	}

	public void addVertex(final V v) {
		this.vertices.add(v);
	}

	public void addEdge(final V v1, final V v2) {
		this.edges.add(new Edge<V>(v1, v2));
	}

	public void validate() {
		checkVertices();
		checkEdges();
	}

	private void checkVertices() {
		if (vertices.isEmpty()) {
			throw new InvalidGraphException("Graph is empty!");
		}
	}

	private void checkEdges() {
		if (edges.size() != edges.parallelStream().distinct().count()) {
			throw new InvalidGraphException("Graph has duplicated edges");
		}
		final List<V> verticesFromEdges = edges.stream()
				.map(e -> Arrays.asList(e.getV1(), e.getV2()))
				.reduce(new ArrayList<>(), ListUtils::union);
		verticesFromEdges.removeAll(vertices);
		if (!verticesFromEdges.isEmpty()) {
			throw new InvalidGraphException("Graph has edges with unknown vertices");
		}
	}
}
