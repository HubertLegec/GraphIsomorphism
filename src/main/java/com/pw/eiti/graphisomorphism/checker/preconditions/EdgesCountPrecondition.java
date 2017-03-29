package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;

public class EdgesCountPrecondition implements Precondition {
	@Override
	public <V> boolean fullfils(final Graph<V> a, final Graph<V> b) {
		return a.getEdgesCount() == b.getEdgesCount();
	}
}