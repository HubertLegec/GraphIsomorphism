package com.pw.eiti.graphisomorphism.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class VertexDegreeTest {

	@Test
	public void testEquals_notEqualWhenDifferentClass() throws Exception {
		// given
		final VertexDegree a = new VertexDegree();
		final Integer integer = new Integer(10);
		// when
		final boolean equals = a.equals(integer);
		// then
		assertThat(equals).isFalse();
	}

	@Test
	public void testEquals_notEqualWhenDifferentOutgoingDegree() throws Exception {
		// given
		final VertexDegree a = new VertexDegree();
		a.setInDeg(10);
		a.setOutDeg(100);
		final VertexDegree b = new VertexDegree();
		b.setInDeg(10);
		b.setOutDeg(99);
		// when
		final boolean equals = a.equals(b);
		// then
		assertThat(equals).isFalse();
	}

	@Test
	public void testEquals_notEqualWhenDifferentIncomingDegree() throws Exception {
		// given
		final VertexDegree a = new VertexDegree();
		a.setInDeg(10);
		a.setOutDeg(100);
		final VertexDegree b = new VertexDegree();
		b.setInDeg(9);
		b.setOutDeg(100);
		// when
		final boolean equals = a.equals(b);
		// then
		assertThat(equals).isFalse();
	}

	@Test
	public void testEquals_equalWhenSameDegrees() throws Exception {
		// given
		final VertexDegree a = new VertexDegree();
		a.setInDeg(10);
		a.setOutDeg(100);
		final VertexDegree b = new VertexDegree();
		b.setInDeg(10);
		b.setOutDeg(100);
		// when
		final boolean equals = a.equals(b);
		// then
		assertThat(equals).isTrue();
	}

}
