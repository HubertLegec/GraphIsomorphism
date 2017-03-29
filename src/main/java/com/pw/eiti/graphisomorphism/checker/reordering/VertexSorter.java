package com.pw.eiti.graphisomorphism.checker.reordering;

import java.util.Collections;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimaps;
import com.pw.eiti.graphisomorphism.checker.DegreeCalculator;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

/**
 * Class used for sorting vertices in a graph.
 */
public class VertexSorter {
	private final DegreeCalculator degreeCalc;

	public VertexSorter(final DegreeCalculator degreeCalc) {
		this.degreeCalc = degreeCalc;
	}

	/**
	 * Sorts graph vertices so that vertices with lower number of
	 * possitble permutational assignments to other vertcies are first.
	 *
	 * @param g graph which verticles are to be sorted
	 */
	public <V> void sortVerticles(final Graph<V> g){
		final Map<V, VertexDegree> vertexToDegreeMap = degreeCalc.getDegrees(g);
		final ArrayListMultimap<VertexDegree, V> degreeToVerticlesMultimap =
				Multimaps.invertFrom(Multimaps.forMap(vertexToDegreeMap), ArrayListMultimap.create());
		Collections.sort(g.getVertices(), (a, b) -> {
			final VertexDegree aDegree = vertexToDegreeMap.get(a);
			final VertexDegree bDegree = vertexToDegreeMap.get(b);
			final Integer aAssignmentsCount = degreeToVerticlesMultimap.get(aDegree).size();
			final Integer bAssignmentsCount = degreeToVerticlesMultimap.get(bDegree).size();
			return aAssignmentsCount.compareTo(bAssignmentsCount);
		});
	}
}