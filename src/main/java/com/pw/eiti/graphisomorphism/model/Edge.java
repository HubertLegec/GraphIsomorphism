package com.pw.eiti.graphisomorphism.model;

import java.util.Objects;

/**
 * Edge that joins two vertices.
 *
 * @param <V> Type of vertices that are joined by edge.
 */
public class Edge<V> {
	private final V v1;
	private final V v2;

	public Edge(final V v1, final V v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	public V getV1() {
		return v1;
	}

	public V getV2() {
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
		final Edge<V> other = (Edge<V>) obj;
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
