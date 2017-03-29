package com.pw.eiti.graphisomorphism.model;


import org.junit.Assert;
import org.junit.Test;

public class EdgeTest {

	@Test
	public void equalsElementsTest() {
		final Edge<String> e1 = new Edge<>("a", "b");
		final Edge<String> e2 = new Edge<>("a", "b");

		Assert.assertEquals(e1, e2);
	}

	@Test
	public void differentElementsTest() {
		final Edge<String> e1 = new Edge<>("a", "b");
		final Edge<String> e2 = new Edge<>("a", "c");

		Assert.assertNotEquals(e1, e2);
	}
}
