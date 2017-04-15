package com.pw.eiti.graphisomorphism.checker;

import com.pw.eiti.graphisomorphism.checker.preconditions.Precondition;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatcherFactory;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatching;
import com.pw.eiti.graphisomorphism.model.Graph;

import java.util.Optional;

/**
 * Class responsible for checking if graphs are isomorphic.
 */
public class GraphIsomorphismChecker {

	private final Precondition precondition;
	private final VertexMatcherFactory vertexMatcherFactory;

	/**
	 * @param precondition
	 *            precondition that has to be fulfilled for graphs to be
	 *            isomorphic.
	 */
	public GraphIsomorphismChecker(final Precondition precondition,
			final VertexMatcherFactory vertexMatcherFactory) {
		this.precondition = precondition;
		this.vertexMatcherFactory = vertexMatcherFactory;
	}

	/**
	 * Gets isomorphic relation for two graphs.
	 *
	 * @param first first graph for isomorphic relation
	 * @param second second graph for isomorphic relation
	 * @return empty id graphs are not isomorphic, or vertex matching if they are
	 */
	public Optional<VertexMatching> getIsomorphism(final Graph first, final Graph second) {
		return Optional.of(precondition.fulfills(first, second))
				.filter(Boolean::booleanValue)
				.map(v -> vertexMatcherFactory.getVertexMatcher(first))
				.map(matcher -> matcher.getMatchingTo(second))
				.filter(Optional::isPresent)
				.map(Optional::get);
	}
}