package com.pw.eiti.graphisomorphism.checker;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class used for checking preconditions of graph isomorhism.
 */
public class PreconditionsChecker {

	private final DegreeCalculator degreeCalc;
	private final Collection<Predocndition> preconditions;

	public PreconditionsChecker(final DegreeCalculator degreeCalc) {
		this.degreeCalc = degreeCalc;
		preconditions = Lists.newArrayList(new VerticesCountPrecondition(), new EdgesCountPrecondition(),
				new DegreePrecondition());
	}

	public boolean fullfils(final Graph a, final Graph b) {
		return preconditions.stream().allMatch(p -> p.fullfils(a, b));
	}

	private interface Predocndition {
		boolean fullfils(final Graph a, final Graph b);
	}

	private class VerticesCountPrecondition implements Predocndition {
		@Override
		public boolean fullfils(final Graph a, final Graph b) {
			return a.getVerticesCount() == b.getVerticesCount();
		}
	}

	private class EdgesCountPrecondition implements Predocndition {
		@Override
		public boolean fullfils(final Graph a, final Graph b) {
			return a.getEdgesCount() == b.getEdgesCount();
		}
	}

	private class DegreePrecondition implements Predocndition {
		@Override
		public boolean fullfils(final Graph a, final Graph b) {
			final Map<String, Integer> aDist = degreeCalc.getDegrees(a);
			final Map<String, Integer> bDist = degreeCalc.getDegrees(b);
			final HashMultiset<Object> aDegreeDist = HashMultiset.create();
			final HashMultiset<Object> bDegreeDist = HashMultiset.create();
			aDegreeDist.addAll(aDist.values());
			bDegreeDist.addAll(bDist.values());
			return aDegreeDist.equals(bDist);
		}
	}
}
