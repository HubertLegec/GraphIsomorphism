package com.pw.eiti.graphisomorphism.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Instances of this class performa matching of one graph's vertices
 * to other graph's vertices.
 */
public class VertexMatcher {
	private final ArrayList<VertexMatchRequirement> requirements;
	private VertexMatching matching;

	public VertexMatcher(final ArrayList<VertexMatchRequirement> requirements){
		this.requirements = requirements;
	}

	public Optional<VertexMatching> getMatchingTo(final Graph graph) {
		matching = new VertexMatching(requirements.size());
		matchRecursiveTo(graph);
		if(matching.isComplete()) {
			return Optional.of(matching);
		}
		return Optional.empty();
	}

	private void matchRecursiveTo(final Graph graph) {
		if (matching.isComplete()) {
			return;
		}
		for (final Integer vDst : graph.getVertices()) {
			if(matching.getSrcByDst(vDst) != null) {
				continue;
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
		}
	}

	private List<Edge> getEdgesRequiredForNextToMatch(final Integer vSecond){
		addNextMatch(vSecond);
		final VertexMatchRequirement requirement = getLastRequirement();
		final List<Edge> destEdges = getDstEdges(requirement.getEdgesToMatch());
		removeLastMatch();
		return destEdges;
	}

	private List<Edge> getDstEdges(final List<Edge> soruceEdges) {
		return soruceEdges.stream()
				.map(this::getDstEdge)
				.collect(Collectors.toList());
	}

	private Edge getDstEdge(final Edge sourceEdge) {
		final Integer dstV1 = matching.getDstBySrc(sourceEdge.getV1());
		final Integer dstV2 = matching.getDstBySrc(sourceEdge.getV2());
		return new Edge(dstV1, dstV2);
	}

	private void addNextMatch(final int vDst) {
		final VertexMatchRequirement nextRequirement = getNextRequirement();
		final Integer vSrc = nextRequirement.getVertexToMatch();
		this.matching.add(vSrc, vDst);
	}

	private void removeLastMatch() {
		final VertexMatchRequirement lastRequirement = getLastRequirement();
		final Integer vSrc = lastRequirement.getVertexToMatch();
		this.matching.remove(vSrc);
	}

	private VertexMatchRequirement getNextRequirement() {
		return this.requirements.get(this.matching.getMatchedVerticesCount());
	}

	private VertexMatchRequirement getLastRequirement() {
		return this.requirements.get(this.matching.getMatchedVerticesCount() - 1);
	}
}