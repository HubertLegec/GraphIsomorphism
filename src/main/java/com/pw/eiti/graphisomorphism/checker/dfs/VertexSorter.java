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
public class VertexSorter {
    private final DegreeCalculator degreeCalc;

    public VertexSorter(final DegreeCalculator degreeCalc) {
        this.degreeCalc = degreeCalc;
    }

    /**
     * Sorts graph vertices so that vertices with lower number of
     * possible permutational assignments to other vertices are first.
     *
     * @param g graph which vertices are to be sorted
     * @return sorted vertices list
     */
    public List<Integer> getSortedVertices(final Graph g) {
        final Map<Integer, VertexDegree> vertexToDegreeMap = degreeCalc.getDegrees(g);
        final Multimap<VertexDegree, Integer> degreeToVerticesMultimap =
                Multimaps.invertFrom(Multimaps.forMap(vertexToDegreeMap), ArrayListMultimap.create());
        return g.getVertices()
                .stream()
                .sorted((a, b) -> {
                    final VertexDegree aDegree = vertexToDegreeMap.get(a);
                    final VertexDegree bDegree = vertexToDegreeMap.get(b);
                    final Integer aAssignmentsCount = degreeToVerticesMultimap.get(aDegree).size();
                    final Integer bAssignmentsCount = degreeToVerticesMultimap.get(bDegree).size();
                    return aAssignmentsCount.compareTo(bAssignmentsCount);
                }).collect(toList());
    }
}