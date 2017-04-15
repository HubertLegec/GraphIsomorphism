package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Instances of this class performs matching of one graph's vertices
 * to other graph's vertices.
 */
public class VertexMatcher {
    private final List<VertexMatchRequirement> requirements;
    private VertexMatching matching;

    VertexMatcher(final List<VertexMatchRequirement> requirements) {
        this.requirements = requirements;
    }

    public Optional<VertexMatching> getMatchingTo(final Graph graph) {
        matching = new VertexMatching(requirements.size());
        matchRecursiveTo(graph);
        return Optional.of(matching)
                .filter(VertexMatching::isComplete);
    }

    private void matchRecursiveTo(final Graph graph) {
        if (matching.isComplete()) {
            return;
        }
        graph.getVertices()
                .forEach(vDst -> {
                    if (matching.getSrcByDst(vDst) != null) {
                        return;
                    }
                    final List<Edge> edgesRequired = getEdgesRequiredForNextToMatch(vDst);
                    if (graph.containsAllEdges(edgesRequired)) {
                        addNextMatch(vDst);
                        matchRecursiveTo(graph);
                        if (matching.isComplete()) {
                            return;
                        }
                        removeLastMatch();
                    }
                });
    }

    private List<Edge> getEdgesRequiredForNextToMatch(final Integer vSecond) {
        addNextMatch(vSecond);
        final VertexMatchRequirement requirement = getLastRequirement();
        final List<Edge> destEdges = getDstEdges(requirement.getEdgesToMatch());
        removeLastMatch();
        return destEdges;
    }

    private List<Edge> getDstEdges(final List<Edge> sourceEdges) {
        return sourceEdges.stream()
                .map(this::getDstEdge)
                .collect(toList());
    }

    private Edge getDstEdge(final Edge sourceEdge) {
        final Integer dstV1 = matching.getDstBySrc(sourceEdge.getV1());
        final Integer dstV2 = matching.getDstBySrc(sourceEdge.getV2());
        return new Edge(dstV1, dstV2);
    }

    private void addNextMatch(final int vDst) {
        final VertexMatchRequirement nextRequirement = getNextRequirement();
        final Integer vSrc = nextRequirement.getVertexToMatch();
        matching.add(vSrc, vDst);
    }

    private void removeLastMatch() {
        final VertexMatchRequirement lastRequirement = getLastRequirement();
        final Integer vSrc = lastRequirement.getVertexToMatch();
        matching.remove(vSrc);
    }

    private VertexMatchRequirement getNextRequirement() {
        return requirements.get(matching.getMatchedVerticesCount());
    }

    private VertexMatchRequirement getLastRequirement() {
        return requirements.get(matching.getMatchedVerticesCount() - 1);
    }
}