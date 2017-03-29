package com.pw.eiti.graphisomorphism.checker;

import java.util.HashMap;
import java.util.Map;

import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

/**
 * Class used for calculating degree distribution of graph
 */
public class DegreeCalculator {
	/**
	 * Gets a map of verticle lables to their degree value.
	 *
	 * @param a
	 *            graph which vertices degrees are to be calculated
	 * @return graph distribution
	 */
	public <V> Map<V, VertexDegree> getDegrees(final Graph<V> a) {
		final Map<V, VertexDegree> map = new HashMap<>();
		for (final Edge<V> edge : a.getEdges()) {
			if (!map.containsKey(edge.getV1())) {
				map.put(edge.getV1(), new VertexDegree());
			}
			if (!map.containsKey(edge.getV2())) {
				map.put(edge.getV2(), new VertexDegree());
			}
			map.get(edge.getV1()).incrementOutDeg();
			map.get(edge.getV2()).incrementInDeg();
		}
		return map;
	}
}
