package com.pw.eiti.graphisomorphism.checker.preconditions;

import java.util.Collection;

import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class used for checking preconditions of graph isomorhism.
 */
public class GraphIsomorphismPreconditionCollection implements Precondition {

	private final Collection<Precondition> preconditions;

	public GraphIsomorphismPreconditionCollection(final Collection<Precondition> preconditions) {
		this.preconditions = preconditions;
	}

	@Override
	public boolean fullfils(final Graph a, final Graph b) {
		return preconditions.stream().allMatch(p -> p.fullfils(a, b));
	}
}
