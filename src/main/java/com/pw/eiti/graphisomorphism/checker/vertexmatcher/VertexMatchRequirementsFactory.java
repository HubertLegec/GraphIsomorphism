package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.pw.eiti.graphisomorphism.checker.dfs.DFSPreOrderBuilder;
import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class VertexMatchRequirementsFactory {
    private static final Logger log = Logger.getLogger(VertexMatchRequirementsFactory.class);
    private final DFSPreOrderBuilder dfsPreOrderBuilder;

    public VertexMatchRequirementsFactory(final DFSPreOrderBuilder dfsPreOrderBuilder) {
        this.dfsPreOrderBuilder = dfsPreOrderBuilder;
    }

    /**
     * Returns an ordered list of requirements that must be fulfilled for each vertex
     * to conclude that some graph is isomorphic to specified graph.
     *
     * @param graph graph for which requirements are to be calculated
     * @return requirements for isomorphism to specified graph
     */
    List<VertexMatchRequirement> getRequirementsFor(final Graph graph) {
        log.info("Get requirements for graph: " + graph.getName());
        final Map<Integer, Integer> dfsPreOrder = dfsPreOrderBuilder.getVertexToDFSPreOrder(graph);
        final List<Integer> verticesByPreOrder = getVerticesByPreOrder(graph, dfsPreOrder);
        final Multimap<Integer, Edge> edgesForVertexRequirements = getEdgesForVertexRequirements(graph, dfsPreOrder);
        return verticesByPreOrder.stream()
                .map(vertex -> new VertexMatchRequirement(
                        vertex,
                        new ArrayList<>(edgesForVertexRequirements.get(vertex))
                ))
                .collect(toList());
    }

    private Multimap<Integer, Edge> getEdgesForVertexRequirements(final Graph graph, final Map<Integer, Integer> vertexToDfsPreOrder) {
        log.info("Get edges for vertex requirements in graph: " + graph.getName());
        final Multimap<Integer, Edge> edgesForVertexRequirements = ArrayListMultimap.create();
        graph.getEdges()
                .forEach(edge -> {
                    final Integer v1PreOrder = vertexToDfsPreOrder.get(edge.getV1());
                    final Integer v2PreOrder = vertexToDfsPreOrder.get(edge.getV2());
                    final Integer masterVertex = v1PreOrder > v2PreOrder ? edge.getV1() : edge.getV2();
                    edgesForVertexRequirements.put(masterVertex, edge);
                });
        return edgesForVertexRequirements;
    }

    private List<Integer> getVerticesByPreOrder(final Graph graph, final Map<Integer, Integer> dfsPreOrder) {
        log.info("Get vertices by pre order in graph: " + graph.getName());
        return graph.getVertices()
                .stream()
                .sorted(Comparator.comparing(dfsPreOrder::get))
                .collect(toList());
    }
}