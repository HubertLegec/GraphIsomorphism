package com.pw.eiti.graphisomorphism.checker;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.common.collect.Maps;
import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class used for building DFS graph.
 */
public class DFSPreOrderBuilder {
	private final VertexSorter sorter;

	public DFSPreOrderBuilder(final VertexSorter sorter) {
		this.sorter = sorter;
	}

	public <V> Map<V, Integer> getDFSPreOrder(final Graph<V> graph) {
		final List<V> sortedVerticles = sorter.getSortedVerticles(graph);
		final Map<V, Integer> orderMap = Maps.newHashMap();
		final Stack<V> verticesToProcess = new Stack<>();
		for (final V v : sortedVerticles) {
			if (!orderMap.containsKey(v)) {
				verticesToProcess.add(v);
				processVertices(graph, orderMap, verticesToProcess);
			}
		}
		return orderMap;
	}

	private <V> void processVertices(final Graph<V> graph, final Map<V, Integer> orderMap,
			final Stack<V> verticesToProcess) {
		while (!verticesToProcess.isEmpty()) {
			final V currentV = verticesToProcess.pop();
			if (!orderMap.containsKey(currentV)) {
				processVertex(graph, orderMap, verticesToProcess, currentV);
			}
		}
	}

	private <V> void processVertex(final Graph<V> graph, final Map<V, Integer> orderMap, final Stack<V> verticesToProcess,
			final V currentV) {
		orderMap.put(currentV, orderMap.size());
		final Set<V> neighbours = graph.getNeighbours(currentV);
		for (final V neighbour : neighbours) {
			verticesToProcess.push(neighbour);
		}
	}
}