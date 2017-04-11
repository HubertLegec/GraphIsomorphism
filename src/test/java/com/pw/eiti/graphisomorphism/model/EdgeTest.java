package com.pw.eiti.graphisomorphism.model;


import org.junit.Assert;
import org.junit.Test;

public class EdgeTest {

	@Test
	public void equalsElementsTest() {
		final Edge e1 = new Edge(1, 2);
		final Edge e2 = new Edge(1, 2);

		Assert.assertEquals(e1, e2);
	}

	@Test
	public void differentElementsTest() {
		final Edge e1 = new Edge(0, 1);
		final Edge e2 = new Edge(0, 2);

		Assert.assertNotEquals(e1, e2);
	}
}
