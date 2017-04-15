package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import com.pw.eiti.graphisomorphism.model.Edge;

import java.util.List;

class VertexMatchRequirement {
	private final Integer vertexToMatch;
	private final List<Edge> edgesToMatch;

	/**
	 * @param vertexToMatch vertex to which this requirement should be applied
	 * @param edgesToMatch edges which should exist for this requirement to be fulfilled
	 */
	VertexMatchRequirement(final Integer vertexToMatch, final List<Edge> edgesToMatch)
	{
		this.vertexToMatch = vertexToMatch;
		this.edgesToMatch = edgesToMatch;
	}

	Integer getVertexToMatch() {
		return vertexToMatch;
	}

	List<Edge> getEdgesToMatch() {
		return edgesToMatch;
	}
}
