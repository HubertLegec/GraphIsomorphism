package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import com.pw.eiti.graphisomorphism.model.Graph;
import org.apache.log4j.Logger;

import java.util.List;

public class VertexMatcherFactory {
	private static final Logger log = Logger.getLogger(VertexMatcherFactory.class);
	private final VertexMatchRequirementsFactory requirementsFactory;

	public VertexMatcherFactory(final VertexMatchRequirementsFactory requirementsFactory) {
		this.requirementsFactory = requirementsFactory;
	}

	public VertexMatcher getVertexMatcher(final Graph graph) {
	    log.info("Get vertex matcher for graph: " + graph.getName());
		final List<VertexMatchRequirement> requirements = requirementsFactory.getRequirementsFor(graph);
		return new VertexMatcher(requirements);
	}
}
