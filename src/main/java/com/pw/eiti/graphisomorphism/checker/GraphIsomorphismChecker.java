package com.pw.eiti.graphisomorphism.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class responsible for checking if graphs are isomorphic.
 */
public class GraphIsomorphismChecker {
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
	public List<GraphIsomorphismDefinition> getIsomorhism(final Collection<Graph> graphs)
			throws OperationNotSupportedException {
		final List<GraphIsomorphismDefinition> result = new ArrayList<>();
		for (final Graph first : graphs)
			for (final Graph second : graphs)
				if (first != second) {
					final GraphIsomorphismDefinition isomorhism = getIsomorhism(first, second);
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
	public GraphIsomorphismDefinition getIsomorhism(final Graph first, final Graph second)
			throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}
}
