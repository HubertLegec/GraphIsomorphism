package com.pw.eiti.graphisomorphism.checker;

import java.util.Optional;

import com.pw.eiti.graphisomorphism.checker.preconditions.Precondition;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatcher;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatcherFactory;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatching;
import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class responsible for checking if graphs are isomorphic.
 */
public class GraphIsomorphismChecker {

	private final Precondition precondition;
	private final VertexMatcherFactory vertexMatcherFactory;

	/**
	 * @param precondition
	 *            precondition that has to be fullfiled for graphs to be
	 *            isomorphic.
	 */
	public GraphIsomorphismChecker(final Precondition precondition,
			final VertexMatcherFactory vertexMatcherFactory) {
		this.precondition = precondition;
		this.vertexMatcherFactory = vertexMatcherFactory;
	}

	/**
	 * Gets isomorhic relation for two graphs.
	 *
	 * @param first first graph for isomorhic relation
	 * @param second second graph for isomorhic relation
	 * @return empty id graphs are not isomorphic, or vertex matching if they are
	 */
	public Optional<VertexMatching> getIsomorhism(final Graph first, final Graph second) {
		if (!precondition.fullfils(first, second)) {
			return Optional.empty();
		}
		final VertexMatcher matcher = vertexMatcherFactory.getVertexMatcher(first);
		return matcher.getMatchingTo(second);
	}
}