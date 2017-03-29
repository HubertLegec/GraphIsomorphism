package com.pw.eiti.graphisomorphism.model;

import com.google.common.base.Objects;

/**
 * Degree of vertex in a directed graph.
 */
public class VertexDegree {
	private Integer inDeg = 0;
	private Integer outDeg = 0;

	public VertexDegree() {
	}

	public VertexDegree(final Integer in, final Integer out) {
		this.inDeg = in;
		this.outDeg = out;
	}

	public Integer getInDeg() {
		return inDeg;
	}

	public void setInDeg(final Integer incomingDegree) {
		this.inDeg = incomingDegree;
	}

	public Integer getOutDeg() {
		return outDeg;
	}

	public void setOutDeg(final Integer outgoingDegree) {
		this.outDeg = outgoingDegree;
	}

	public void incrementInDeg() {
		inDeg++;
	}

	public void incrementOutDeg() {
		outDeg++;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof VertexDegree)) {
			return false;
		}
		final VertexDegree that = (VertexDegree) obj;
		return Objects.equal(this.inDeg, that.inDeg)
				&& Objects.equal(this.outDeg, that.outDeg);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.inDeg, this.outDeg);
	}
}