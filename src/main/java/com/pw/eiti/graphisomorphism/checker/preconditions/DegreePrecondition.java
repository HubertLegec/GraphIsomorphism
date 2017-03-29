package com.pw.eiti.graphisomorphism.checker.preconditions;

import java.util.Map;

import com.google.common.collect.HashMultiset;
import com.pw.eiti.graphisomorphism.checker.DegreeCalculator;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

public class DegreePrecondition implements Precondition {
	private final DegreeCalculator degreeCalc;

	public DegreePrecondition(final DegreeCalculator degreCalc) {
		this.degreeCalc = degreCalc;
	}

	@Override
	public <V> boolean fullfils(final Graph<V> a, final Graph<V> b) {
		final Map<V, VertexDegree> aDist = degreeCalc.getDegrees(a);
		final Map<V, VertexDegree> bDist = degreeCalc.getDegrees(b);
		final HashMultiset<VertexDegree> aDegreeDist = HashMultiset.create();
		final HashMultiset<VertexDegree> bDegreeDist = HashMultiset.create();
		aDegreeDist.addAll(aDist.values());
		bDegreeDist.addAll(bDist.values());
		return aDegreeDist.equals(bDegreeDist);
	}
}