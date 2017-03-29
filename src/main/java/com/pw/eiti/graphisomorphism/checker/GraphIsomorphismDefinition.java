package com.pw.eiti.graphisomorphism.checker;

import java.util.Map;

import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class that defines the relation of isomorphism.
 */
public class GraphIsomorphismDefinition<V>  {
	private final Graph<V> first;
	private final Graph<V> second;
	private final Map<V, V> verticesMapping;

	public GraphIsomorphismDefinition(final Graph<V> first, final Graph<V> second,
			final Map<V, V> verticesMapping)
	{
		this.first = first;
		this.second = second;
		this.verticesMapping = verticesMapping;
	}

	public Graph<V> getFirst() {
		return first;
	}

	public Graph<V> getSecond() {
		return second;
	}

	public Map<V, V> getVerticesMapping() {
		return verticesMapping;
	}
}