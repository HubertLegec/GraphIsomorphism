package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;

public class VerticesCountPrecondition implements Precondition {
	@Override
	public <V> boolean fullfils(final Graph<V> a, final Graph<V> b) {
		return a.getVerticesCount() == b.getVerticesCount();
	}
}