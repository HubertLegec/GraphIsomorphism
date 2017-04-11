package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import java.util.List;

import com.pw.eiti.graphisomorphism.model.Edge;

public class VertexMatchRequirement {
	private final Integer vertexToMatch;
	private final List<Edge> edgesToMatch;

	/**
	 * @param vertexToMatch vertex to which this requirement should be applied
	 * @param edgesToMatch edges which should exist for this requirement to be
	 * 		fullfiled
	 */
	public VertexMatchRequirement(final Integer vertexToMatch, final List<Edge> edgesToMatch)
	{
		this.vertexToMatch = vertexToMatch;
		this.edgesToMatch = edgesToMatch;
	}

	public Integer getVertexToMatch() {
		return vertexToMatch;
	}

	public List<Edge> getEdgesToMatch() {
		return edgesToMatch;
	}
}
