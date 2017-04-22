package com.pw.eiti.graphisomorphism.checker;

import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class used for calculating degree distribution of graph
 */
public class DegreeCalculator {
    private static final Logger log = Logger.getLogger(DegreeCalculator.class);

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
        log.info("Get degrees for graph: " + a.getName());
        final Map<Integer, VertexDegree> map = new HashMap<>();
        a.getEdges().forEach(edge -> {
            getVertexDegree(map, edge.getV1())
                    .incrementOutDeg();
            getVertexDegree(map, edge.getV2())
                    .incrementInDeg();
        });
        return map;
    }

    private static VertexDegree getVertexDegree(Map<Integer, VertexDegree> map, int vertex) {
        return Optional.ofNullable(map.get(vertex))
                .orElseGet(() -> {
                    VertexDegree d = new VertexDegree();
                    map.put(vertex, d);
                    return d;
                });
    }
}