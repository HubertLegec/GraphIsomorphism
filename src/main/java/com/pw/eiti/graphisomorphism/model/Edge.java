package com.pw.eiti.graphisomorphism.model;

import com.google.gson.annotations.Expose;

import java.util.Objects;

/**
 * Edge that joins two vertices.
 */
public class Edge {
    @Expose
	private final Integer v1;
    @Expose
	private final Integer v2;

	public Edge(final Integer v1, final Integer v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	public Integer getV1() {
		return v1;
	}

	public Integer getV2() {
		return v2;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Edge)) {
			return false;
		}
		final Edge other = (Edge) obj;
		return v1.equals(other.v1) && v2.equals(other.v2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(v1, v2);
	}

	@Override
	public String toString() {
		return "Edge{ v1='" + v1 + "', v2='" + v2 + "' }";
	}
}
