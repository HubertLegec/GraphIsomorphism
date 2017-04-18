package com.pw.eiti.graphisomorphism.model;

import java.util.*;
import java.util.stream.IntStream;

import com.google.common.collect.HashMultimap;

import static java.util.stream.Collectors.toList;

public class Graph {
	private String name;
	private Integer verticesCount;
	private List<Edge> edges;
	private HashMultimap<Integer, Integer> neighbourList;
	private Boolean[][] incidenceMatrix;

	public int getVerticesCount() {
		return this.verticesCount;
	}

	public void setVerticesCount(final Integer verticesCount) {
		this.verticesCount = verticesCount;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<Edge> getEdges() {
		return this.edges;
	}

	public void setEdges(final List<Edge> edges) {
		this.edges = edges;
		this.incidenceMatrix = new Boolean[this.verticesCount][];
		for(int i = 0; i < this.verticesCount; ++i) {
			this.incidenceMatrix[i] = Collections
                    .nCopies(this.verticesCount, false)
                    .toArray(new Boolean[0]);
		}
		this.neighbourList = HashMultimap.create();
		edges.forEach(edge -> {
            this.incidenceMatrix[edge.getV1()][edge.getV2()] = true;
            this.neighbourList.put(edge.getV1(), edge.getV2());
        });
	}

	public void reload() {
		this.incidenceMatrix = new Boolean[this.verticesCount][];
		for(int i = 0; i < this.verticesCount; ++i) {
			this.incidenceMatrix[i] = Collections
					.nCopies(this.verticesCount, false)
					.toArray(new Boolean[0]);
		}
		this.neighbourList = HashMultimap.create();
		edges.forEach(edge -> {
			this.incidenceMatrix[edge.getV1()][edge.getV2()] = true;
			this.neighbourList.put(edge.getV1(), edge.getV2());
		});
	}

	public Set<Integer> getNeighbours(final Integer v) {
		return this.neighbourList.get(v);
	}

	public List<Integer> getVertices() {
		return IntStream
				.range(0, this.verticesCount)
				.boxed()
				.collect(toList());
	}

	public boolean containsEdge(final Edge edge) {
		return this.incidenceMatrix[edge.getV1()][edge.getV2()];
	}

	public boolean containsAllEdges(final Collection<Edge> edges) {
		return edges
				.stream()
				.allMatch(this::containsEdge);
	}
}