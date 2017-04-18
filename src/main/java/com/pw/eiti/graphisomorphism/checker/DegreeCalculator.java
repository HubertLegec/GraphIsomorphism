package com.pw.eiti.graphisomorphism.checker;

import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class used for calculating degree distribution of graph
 */
public class DegreeCalculator {

    /**
     * There is no need to create instance of this class
     */
    private DegreeCalculator() {}

    /**
     * Gets a map of vertex labels to their degree value.
     *
     * @param a graph which vertices degrees are to be calculated
     * @return graph distribution
     */
    public static Map<Integer, VertexDegree> getDegrees(final Graph a) {
        final Map<Integer, VertexDegree> map = new HashMap<>();
        a.getEdges().forEach(edge -> {
            Optional.ofNullable(map.get(edge.getV1()))
                    .orElseGet(() -> {
                        VertexDegree d = new VertexDegree();
                        map.put(edge.getV1(), d);
                        return d;
                    })
                    .incrementOutDeg();
            Optional.ofNullable(map.get(edge.getV2()))
                    .orElseGet(() -> {
                        VertexDegree d = new VertexDegree();
                        map.put(edge.getV2(), d);
                        return d;
                    })
                    .incrementInDeg();
        });
        return map;
    }
}