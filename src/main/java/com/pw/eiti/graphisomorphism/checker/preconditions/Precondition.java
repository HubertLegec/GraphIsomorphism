package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;

public interface Precondition {
	<V> boolean fullfils(final Graph<V> a, final Graph<V> b);
}