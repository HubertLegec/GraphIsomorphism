package com.pw.eiti.graphisomorphism.checker;

import java.util.HashMap;
import java.util.Map;

import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class used for calculating degree distribution of graph
 */
public class DegreeCalculator {
	/**
	 * Gets a map of verticle lables to their degree value. Degree value
	 * identifies both outgoing and incoming degree, because the outgoing degree
	 * is multiplies by an exponential that is alwats greater that incoming
	 * degree;
	 *
	 * @param a
	 *            graph which vertices degrees are to be calculated
	 * @return graph distribution
	 */
	public Map<String, Integer> getDegrees(final Graph a) {
		final Map<String, Integer> map = new HashMap<>();
		final Integer exponential = a.getEdgesCount() + 1;
		for (final Edge edge : a.getEdges()) {
			if (!map.containsKey(edge.getV1())) {
				map.put(edge.getV1(), 0);
			}
			if (!map.containsKey(edge.getV2())) {
				map.put(edge.getV2(), 0);
			}
			final Integer newV1Dist = map.get(edge.getV1()) + exponential;
			final Integer newV2Dist = map.get(edge.getV2()) + 1;
			map.put(edge.getV1(), newV1Dist);
			map.put(edge.getV2(), newV2Dist);
		}
		return map;
	}
}
