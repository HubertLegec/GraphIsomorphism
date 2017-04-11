package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import java.util.List;

import com.pw.eiti.graphisomorphism.model.Graph;

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
