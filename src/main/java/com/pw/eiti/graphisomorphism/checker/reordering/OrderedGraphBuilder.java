package com.pw.eiti.graphisomorphism.checker.reordering;

import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class used for building reordered graph.
 */
public class OrderedGraphBuilder {
	private final VertexSorter sorter;

	public OrderedGraphBuilder(final VertexSorter sorter) {
		this.sorter = sorter;
	}

	public <V> OrderedGraph<V> getReorderedGraph(final Graph<V> graph){
		sorter.sortVerticles(graph);
		final OrderedGraph<String> reorderedGraph = new OrderedGraph<>();
		return null;
	}
}
