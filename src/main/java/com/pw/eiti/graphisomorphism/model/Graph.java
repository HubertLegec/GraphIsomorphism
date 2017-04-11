package com.pw.eiti.graphisomorphism.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.collect.HashMultimap;

public class Graph {
	private String name;
	private Integer verticesCount;
	private List<Edge> edges;
	private HashMultimap<Integer, Integer> neighbourList;
	private Boolean[][] incidenceMartix;

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
		this.incidenceMartix = new Boolean[this.verticesCount][];
		for(int i = 0; i < this.verticesCount; ++i) {
			this.incidenceMartix[i] = new Boolean[this.verticesCount];
			for(int j = 0; j <this.verticesCount; ++j){
				this.incidenceMartix[i][j] = false;
			}
		}
		this.neighbourList = HashMultimap.create();
		for(final Edge edge : edges){
			this.incidenceMartix[edge.getV1()][edge.getV2()] = true;
			this.neighbourList.put(edge.getV1(), edge.getV2());
		}
	}

	public Set<Integer> getNeighbours(final Integer v) {
		return this.neighbourList.get(v);
	}

	public List<Integer> getVertices() {
		return IntStream.range(0, this.verticesCount).boxed().collect(Collectors.toList());
	}
}