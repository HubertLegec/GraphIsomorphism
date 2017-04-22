package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.pw.eiti.graphisomorphism.checker.DegreeCalculator;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;
import org.apache.log4j.Logger;

import java.util.Map;

public class DegreePrecondition implements Precondition {
	private static final Logger log = Logger.getLogger(DegreePrecondition.class);

	@Override
	public boolean fulfills(final Graph a, final Graph b) {
		final Map<Integer, VertexDegree> aDist = DegreeCalculator.getDegrees(a);
		final Map<Integer, VertexDegree> bDist = DegreeCalculator.getDegrees(b);
		final Multiset<VertexDegree> aDegreeDist = HashMultiset.create(aDist.values());
		final Multiset<VertexDegree> bDegreeDist = HashMultiset.create(bDist.values());
		boolean result = aDegreeDist.equals(bDegreeDist);
		log.info("fulfills: " + result);
		return result;
	}
}