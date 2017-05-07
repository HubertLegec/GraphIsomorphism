package com.pw.eiti.graphisomorphism.model;

import com.google.common.base.Objects;

/**
 * Degree of vertex in a directed graph.
 * It consist of input and output degree of vertex.
 */
public class VertexDegree {
	private int inDeg = 0;
	private int outDeg = 0;

	public VertexDegree() {
	}

	public VertexDegree(final int in, final int out) {
		this.inDeg = in;
		this.outDeg = out;
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