package com.pw.eiti.graphisomorphism.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import com.pw.eiti.graphisomorphism.checker.preconditions.Precondition;
import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class responsible for checking if graphs are isomorphic.
 */
public class GraphIsomorphismChecker {

	private final Precondition precondition;

	/**
	 * @param precondition
	 *            precondition that has to be fullfiled for graphs to be
	 *            isomorphic.
	 */
	public GraphIsomorphismChecker(final Precondition precondition) {
		this.precondition = precondition;
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
	public <V> List<GraphIsomorphismDefinition<V>> getIsomorhism(final Collection<Graph<V>> graphs) {
		final List<GraphIsomorphismDefinition<V>> result = new ArrayList<>();
		for (final Graph<V> first : graphs)
			for (final Graph<V> second : graphs)
				if (first != second) {
					final GraphIsomorphismDefinition<V> isomorhism = getIsomorhism(first, second);
					if (isomorhism == null) {
						return null;
					}
					result.add(isomorhism);
				}
		return result;
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
	public <V> GraphIsomorphismDefinition<V> getIsomorhism(final Graph<V> first, final Graph<V> second) {
		if(precondition.fullfils(first, second)) {
			return null;
		}
		return null;
	}
}