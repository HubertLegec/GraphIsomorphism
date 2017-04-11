package com.pw.eiti.graphisomorphism.checker.dfs;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.common.collect.Maps;
import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class used for getting DFS PreOrder order of graph's vertices.
 */
public class DFSPreOrderBuilder {
	private final VertexSorter sorter;

	public DFSPreOrderBuilder(final VertexSorter sorter) {
		this.sorter = sorter;
	}

	public Map<Integer, Integer> getVertexToDFSPreOrder(final Graph graph) {
		final List<Integer> sortedVerticles = sorter.getSortedVerticles(graph);
		final Map<Integer, Integer> orderMap = Maps.newHashMap();
		final Stack<Integer> verticesToProcess = new Stack<>();
		for (final Integer v : sortedVerticles) {
			if (!orderMap.containsKey(v)) {
				verticesToProcess.add(v);
				processVertices(graph, orderMap, verticesToProcess);
			}
		}
		return orderMap;
	}

	private void processVertices(final Graph graph, final Map<Integer, Integer> orderMap,
			final Stack<Integer> verticesToProcess) {
		while (!verticesToProcess.isEmpty()) {
			final Integer currentV = verticesToProcess.pop();
			if (!orderMap.containsKey(currentV)) {
				processVertex(graph, orderMap, verticesToProcess, currentV);
			}
		}
	}

	private void processVertex(final Graph graph, final Map<Integer, Integer> orderMap, final Stack<Integer> verticesToProcess,
			final Integer currentV) {
		orderMap.put(currentV, orderMap.size());
		final Set<Integer> neighbours = graph.getNeighbours(currentV);
		for (final Integer neighbour : neighbours) {
			verticesToProcess.push(neighbour);
		}
	}
}