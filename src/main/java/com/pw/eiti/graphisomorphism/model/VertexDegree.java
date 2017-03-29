package com.pw.eiti.graphisomorphism.model;

import com.google.common.base.Objects;

/**
 * Degree of vertex in a directed graph.
 */
public class VertexDegree {
	private Integer incomingDegree = 0;
	private Integer outgoingDegree = 0;

	public VertexDegree() {
	}

	public VertexDegree(final Integer in, final Integer out) {
		this.incomingDegree = in;
		this.outgoingDegree = out;
	}

	public Integer getInDegree() {
		return incomingDegree;
	}

	public void setInDegree(final Integer incomingDegree) {
		this.incomingDegree = incomingDegree;
	}

	public Integer getOutDegree() {
		return outgoingDegree;
	}

	public void setOutDegree(final Integer outgoingDegree) {
		this.outgoingDegree = outgoingDegree;
	}

	public void incrementInDegree() {
		incomingDegree++;
	}

	public void incrementOutDegree() {
		outgoingDegree++;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof VertexDegree)) {
			return false;
		}
		final VertexDegree that = (VertexDegree) obj;
		return Objects.equal(this.incomingDegree, that.incomingDegree)
				&& Objects.equal(this.outgoingDegree, that.outgoingDegree);
	}
}
