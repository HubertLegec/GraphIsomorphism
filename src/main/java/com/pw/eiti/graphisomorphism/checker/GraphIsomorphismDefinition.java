package com.pw.eiti.graphisomorphism.checker;

import java.util.Map;

import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class that defines the relation of isomorphism.
 */
public class GraphIsomorphismDefinition {
	private final Graph first;
	private final Graph second;
	private final Map<String, String> verticesMapping;

	public GraphIsomorphismDefinition(final Graph first, final Graph second,
			final Map<String, String> verticesMapping)
	{
		this.first = first;
		this.second = second;
		this.verticesMapping = verticesMapping;
	}

	public Graph getFirst() {
		return first;
	}

	public Graph getSecond() {
		return second;
	}

	public Map<String, String> getVerticesMapping() {
		return verticesMapping;
	}
}