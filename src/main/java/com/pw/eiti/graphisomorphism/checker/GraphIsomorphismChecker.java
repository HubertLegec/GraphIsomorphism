package com.pw.eiti.graphisomorphism.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import com.pw.eiti.graphisomorphism.checker.preconditions.Precondition;
import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class responsible for checking if graphs are isomorphic.
 */
public class GraphIsomorphismChecker {

	private final Precondition precondition;
	private final VertexMatcherBuilder vertexMatcherBuilder;

	/**
	 * @param precondition
	 *            precondition that has to be fullfiled for graphs to be
	 *            isomorphic.
	 */
	public GraphIsomorphismChecker(final Precondition precondition,
			final VertexMatcherBuilder vertexMatchiRequirementsBuilder) {
		this.precondition = precondition;
		this.vertexMatcherBuilder = vertexMatchiRequirementsBuilder;
	}

	/**
	 * Gets isomorhism reslations for a collection of graphs. Collection is
	 * isomorhic only if all pairs of graphs are isomorphic.
	 *
	 * @param graphs
	 *            graphs for which isomorphism is to be checked
	 * @return null if graphs are not isomorhic, or
	 *         {@link GraphIsomorphismDefinition} for each pair of graphs if
	 *         they are isomorhic.
	 * @throws OperationNotSupportedException
	 *             not yet implemented
	 */
	public Optional<List<VertexMatching>> getIsomorhism(final Collection<Graph> graphs) {
		final List<VertexMatching> result = new ArrayList<>();
		for (final Graph first : graphs)
			for (final Graph second : graphs)
				if (first != second) {
					final Optional<VertexMatching> isomorhism = getIsomorhism(first, second);
					if (!isomorhism.isPresent()) {
						return Optional.empty();
					}
					result.add(isomorhism.get());
				}
		return Optional.of(result);
	}

	/**
	 * Gets isomorhic relation for two graphs.
	 *
	 * @param first
	 *            first graph for isomorhic relation
	 * @param second
	 *            second graph for isomorhic relation
	 * @return null if graphs are not isomorhic, or
	 *         {@link GraphIsomorphismDefinition} for graphs if they are
	 *         isomorhic.
	 * @throws OperationNotSupportedException
	 *             not yet implemented
	 */
	public Optional<VertexMatching> getIsomorhism(final Graph first, final Graph second) {
		if (!precondition.fullfils(first, second)) {
			return Optional.empty();
		}
		final VertexMatcher matcher = vertexMatcherBuilder.getMatcherFor(first);
		return matcher.getMatchingTo(second);
	}
}