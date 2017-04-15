package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;

public interface Precondition {
	boolean fulfills(final Graph a, final Graph b);
}