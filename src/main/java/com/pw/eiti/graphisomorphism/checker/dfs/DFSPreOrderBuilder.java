package com.pw.eiti.graphisomorphism.checker.dfs;

import com.pw.eiti.graphisomorphism.model.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Class used for getting DFS PreOrder order of graph's vertices.
 */
public class DFSPreOrderBuilder {

    public Map<Integer, Integer> getVertexToDFSPreOrder(final Graph graph) {
        final List<Integer> sortedVertices = VertexSorter.getSortedVertices(graph);
        final Map<Integer, Integer> orderMap = new HashMap<>();
        final Stack<Integer> verticesToProcess = new Stack<>();
        sortedVertices.stream()
                .filter(v -> !orderMap.containsKey(v))
                .forEach(v -> {
                    verticesToProcess.add(v);
                    processVertices(graph, orderMap, verticesToProcess);
                });
        return orderMap;
    }

    private void processVertices(final Graph graph, final Map<Integer, Integer> orderMap,
                                 final Stack<Integer> verticesToProcess) {
        while (!verticesToProcess.isEmpty()) {
            final Integer currentV = verticesToProcess.pop();
            if (!orderMap.containsKey(currentV)) {
                processVertex(graph, orderMap, verticesToProcess, currentV);
            }
        }
    }

    private void processVertex(final Graph graph, final Map<Integer, Integer> orderMap, final Stack<Integer> verticesToProcess,
                               final Integer currentV) {
        orderMap.put(currentV, orderMap.size());
        graph.getNeighbours(currentV)
                .forEach(verticesToProcess::push);
    }
}