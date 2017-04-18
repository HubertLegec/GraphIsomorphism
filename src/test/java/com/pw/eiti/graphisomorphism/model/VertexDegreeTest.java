package com.pw.eiti.graphisomorphism.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class VertexDegreeTest {

	@Test
	public void testEquals_notEqualWhenDifferentClass() throws Exception {
		// given
		final VertexDegree a = new VertexDegree();
		final Integer integer = 10;
		// when
		final boolean equals = a.equals(integer);
		// then
		assertThat(equals).isFalse();
	}

	@Test
	public void testEquals_notEqualWhenDifferentOutgoingDegree() throws Exception {
		// given
		final VertexDegree a = new VertexDegree(10, 100);
		final VertexDegree b = new VertexDegree(10, 99);
		// when
		final boolean equals = a.equals(b);
		// then
		assertThat(equals).isFalse();
	}

	@Test
	public void testEquals_notEqualWhenDifferentIncomingDegree() throws Exception {
		// given
		final VertexDegree a = new VertexDegree(10, 100);
		final VertexDegree b = new VertexDegree(9, 100);
		// when
		final boolean equals = a.equals(b);
		// then
		assertThat(equals).isFalse();
	}

	@Test
	public void testEquals_equalWhenSameDegrees() throws Exception {
		// given
		final VertexDegree a = new VertexDegree(10, 100);
		final VertexDegree b = new VertexDegree(10, 100);
		// when
		final boolean equals = a.equals(b);
		// then
		assertThat(equals).isTrue();
	}

}
