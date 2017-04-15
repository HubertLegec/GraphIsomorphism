package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;

import java.util.Collection;

/**
 * Class used for checking preconditions of graph isomorhism.
 */
public class GraphIsomorphismPreconditionCollection implements Precondition {

	private final Collection<Precondition> preconditions;

	public GraphIsomorphismPreconditionCollection(final Collection<Precondition> preconditions) {
		this.preconditions = preconditions;
	}

	@Override
	public boolean fulfills(final Graph a, final Graph b) {
		return preconditions
				.stream()
				.allMatch(p -> p.fulfills(a, b));
	}
}
