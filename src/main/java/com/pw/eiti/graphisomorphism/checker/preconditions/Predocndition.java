package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;

public interface Predocndition {
	boolean fullfils(final Graph a, final Graph b);
}