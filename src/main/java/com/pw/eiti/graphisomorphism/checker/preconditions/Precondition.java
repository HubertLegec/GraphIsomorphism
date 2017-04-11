package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;

public interface Precondition {
	boolean fullfils(final Graph a, final Graph b);
}