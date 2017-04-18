package com.pw.eiti.graphisomorphism.checker.dfs;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.pw.eiti.graphisomorphism.checker.DegreeCalculator;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.model.VertexDegree;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Class used for sorting vertices in a graph.
 */
class VertexSorter {

    /**
     * There is no need to create instance of this class
     */
    private VertexSorter() {}

    /**
     * Sorts graph vertices so that vertices with lower number of
     * possible permutational assignments to other vertices are first.
     *
     * @param g graph which vertices are to be sorted
     * @return sorted vertices list
     */
    static List<Integer> getSortedVertices(final Graph g) {
        final Map<Integer, VertexDegree> vertexToDegreeMap = DegreeCalculator.getDegrees(g);
        final Multimap<VertexDegree, Integer> degreeToVerticesMultimap =
                Multimaps.invertFrom(Multimaps.forMap(vertexToDegreeMap), ArrayListMultimap.create());
        return g.getVertices()
                .stream()
                .sorted((a, b) -> {
                    final VertexDegree aDegree = vertexToDegreeMap.get(a);
                    final VertexDegree bDegree = vertexToDegreeMap.get(b);
                    final int aAssignmentsCount = degreeToVerticesMultimap.get(aDegree).size();
                    final int bAssignmentsCount = degreeToVerticesMultimap.get(bDegree).size();
                    return Integer.compare(aAssignmentsCount, bAssignmentsCount);
                }).collect(toList());
    }
}