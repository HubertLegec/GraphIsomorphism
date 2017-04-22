package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Instances of this class performs matching of one graph's vertices
 * to other graph's vertices.
 */
public class VertexMatcher {
    private static final Logger log = Logger.getLogger(VertexMatcher.class);
    private final List<VertexMatchRequirement> requirements;
    private VertexMatching matching;

    VertexMatcher(final List<VertexMatchRequirement> requirements) {
        this.requirements = requirements;
    }

    public Optional<VertexMatching> getMatchingTo(final Graph graph) {
        log.info("Get matching to: " + graph.getName());
        matching = new VertexMatching(requirements.size());
        matchRecursiveTo(graph);
        return Optional.of(matching)
                .filter(VertexMatching::isComplete);
    }

    private void matchRecursiveTo(final Graph graph) {
        log.info("Matching recursive to: " + graph.getName());
        if (matching.isComplete()) {
            return;
        }
        graph.getVertices()
                .stream()
                .filter(vDst -> matching.getSrcByDst(vDst) == null)
                .filter(vDst -> {
                    final List<Edge> edgesRequired = getEdgesRequiredForNextToMatch(vDst);
                    return graph.containsAllEdges(edgesRequired);
                })
                .forEach(vDst -> {
                    addNextMatch(vDst);
                    matchRecursiveTo(graph);
                    if (matching.isComplete()) {
                        return;
                    }
                    removeLastMatch();
                });
    }

    private List<Edge> getEdgesRequiredForNextToMatch(final Integer vSecond) {
        log.info("Get edges required for next match: " + vSecond);
        addNextMatch(vSecond);
        final VertexMatchRequirement requirement = getLastRequirement();
        final List<Edge> destEdges = getDstEdges(requirement.getEdgesToMatch());
        removeLastMatch();
        return destEdges;
    }

    private List<Edge> getDstEdges(final List<Edge> sourceEdges) {
        log.info("Get dst edges");
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
        log.info("Add next match: " + vDst);
        final VertexMatchRequirement nextRequirement = getNextRequirement();
        final Integer vSrc = nextRequirement.getVertexToMatch();
        matching.add(vSrc, vDst);
    }

    private void removeLastMatch() {
        log.info("Remove last match");
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