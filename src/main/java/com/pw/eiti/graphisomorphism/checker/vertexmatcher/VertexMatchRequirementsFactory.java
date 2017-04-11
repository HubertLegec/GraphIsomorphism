package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.pw.eiti.graphisomorphism.checker.dfs.DFSPreOrderBuilder;
import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;

public class VertexMatchRequirementsFactory {
	private final DFSPreOrderBuilder dfsPreOrderBuilder;

	public VertexMatchRequirementsFactory(final DFSPreOrderBuilder dfsPreOrderBuilder) {
		this.dfsPreOrderBuilder = dfsPreOrderBuilder;
	}

	/**
	 * Returns an ordered list of requirements that must be fullfiled for each vertex
	 * to conclude that some graph is isomorphic to specified graph.
	 *
	 * @param graph graph for which requirements are to be calculated
	 * @return requirements for isomorhism to specified graph
	 */
	public List<VertexMatchRequirement> getRequirementsFor(final Graph graph) {
		final Map<Integer, Integer> dfsPreOrder = dfsPreOrderBuilder.getVertexToDFSPreOrder(graph);
		final List<Integer> verticesByPreOrder = getVerticesByPreOrder(graph, dfsPreOrder);
		final ArrayListMultimap<Integer, Edge> edgesForVertexRequirements = getEdgesForVertexRequirements(graph, dfsPreOrder);
		final List<VertexMatchRequirement> requirements = Lists.newArrayListWithCapacity(verticesByPreOrder.size());
		for(final Integer vertex : verticesByPreOrder) {
			final List<Edge> edges = edgesForVertexRequirements.get(vertex);
			requirements.add(new VertexMatchRequirement(vertex, edges));
		}
		return requirements;
	}

	private ArrayListMultimap<Integer, Edge> getEdgesForVertexRequirements(final Graph graph, final Map<Integer, Integer> vertexToDfsPreOrder) {
		final ArrayListMultimap<Integer, Edge> edgesForVertexRequirements = ArrayListMultimap.create();
		for(final Edge edge : graph.getEdges()) {
			final Integer v1PreOrder = vertexToDfsPreOrder.get(edge.getV1());
			final Integer v2PreOrder = vertexToDfsPreOrder.get(edge.getV2());
			final Integer masterVertex = v1PreOrder > v2PreOrder ? edge.getV1() : edge.getV2();
			edgesForVertexRequirements.put(masterVertex, edge);
		}
		return edgesForVertexRequirements;
	}

	private List<Integer> getVerticesByPreOrder(final Graph graph, final Map<Integer, Integer> dfsPreOrder) {
		return graph.getVertices().stream()
				.sorted((a, b) -> dfsPreOrder.get(a).compareTo(dfsPreOrder.get(b)))
				.collect(Collectors.toList());
	}
}