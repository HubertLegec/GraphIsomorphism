package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import com.pw.eiti.graphisomorphism.model.Graph;

import java.util.List;

public class VertexMatcherFactory {
	private final VertexMatchRequirementsFactory requirementsFactory;

	public VertexMatcherFactory(final VertexMatchRequirementsFactory requirementsFactory) {
		this.requirementsFactory = requirementsFactory;
	}

	public VertexMatcher getVertexMatcher(final Graph graph) {
		final List<VertexMatchRequirement> requirements = requirementsFactory.getRequirementsFor(graph);
		return new VertexMatcher(requirements);
	}
}
