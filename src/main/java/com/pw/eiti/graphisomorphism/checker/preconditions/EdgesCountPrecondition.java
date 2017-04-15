package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;

public class EdgesCountPrecondition implements Precondition {
	@Override
	public boolean fulfills(final Graph a, final Graph b) {
		return a.getEdges().size() == b.getEdges().size();
	}
}