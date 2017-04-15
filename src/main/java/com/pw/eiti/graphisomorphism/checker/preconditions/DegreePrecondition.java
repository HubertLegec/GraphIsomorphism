package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.pw.eiti.graphisomorphism.checker.DegreeCalculator;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

import java.util.Map;

public class DegreePrecondition implements Precondition {
	private final DegreeCalculator degreeCalc;

	public DegreePrecondition(final DegreeCalculator degreeCalc) {
		this.degreeCalc = degreeCalc;
	}

	@Override
	public boolean fulfills(final Graph a, final Graph b) {
		final Map<Integer, VertexDegree> aDist = degreeCalc.getDegrees(a);
		final Map<Integer, VertexDegree> bDist = degreeCalc.getDegrees(b);
		final Multiset<VertexDegree> aDegreeDist = HashMultiset.create();
		final Multiset<VertexDegree> bDegreeDist = HashMultiset.create();
		aDegreeDist.addAll(aDist.values());
		bDegreeDist.addAll(bDist.values());
		return aDegreeDist.equals(bDegreeDist);
	}
}