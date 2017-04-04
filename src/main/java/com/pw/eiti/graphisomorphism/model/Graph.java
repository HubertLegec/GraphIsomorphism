package com.pw.eiti.graphisomorphism.model;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.pw.eiti.graphisomorphism.ui.InvalidGraphException;

public class Graph<V> {
	private String name;
	private Set<V> vertices = Sets.newHashSet();
	private final HashMultimap<V, V> neighbours = HashMultimap.create();

	public int getVerticesCount() {
		return vertices.size();
	}

	public int getEdgesCount() {
		return neighbours.size();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<V> getVertices() {
		return ImmutableSet.copyOf(vertices);
	}

	@Deprecated
	public void setVertices(final Set<V> vertices) {
		this.vertices = vertices;
	}

	public List<Edge<V>> getEdges() {
		final List<Edge<V>> edges = neighbours.entries().stream()
				.map(e -> new Edge<>(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
		return ImmutableList.copyOf(edges);
	}

	@Deprecated
	public void setEdges(final List<Edge<V>> edges) {
		neighbours.clear();
		for(final Edge<V> edge : edges) {
			addEdge(edge.getV1(), edge.getV2());
		}
	}

	public void addVertex(final V v) {
		this.vertices.add(v);
	}

	public void addEdge(final V v1, final V v2) {
		neighbours.put(v1, v2);
	}

	public Set<V> getNeighbours(final V v) {
		return neighbours.get(v);
	}

	public boolean contains(final V v) {
		return vertices.contains(v);
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
		final List<V> verticesFromEdges =
				Stream.concat(
						neighbours.entries().stream().map(Entry::getKey),
						neighbours.entries().stream().map(Entry::getValue))
				.distinct().collect(Collectors.toList());
		verticesFromEdges.removeAll(vertices);
		if (!verticesFromEdges.isEmpty()) {
			throw new InvalidGraphException("Graph has edges with unknown vertices");
		}
	}
}
