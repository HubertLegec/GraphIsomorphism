package com.pw.eiti.graphisomorphism.model;


import org.junit.Assert;
import org.junit.Test;

import com.pw.eiti.graphisomorphism.model.Edge;

public class EdgeTest {

    @Test
    public void equalsElementsTest() {
        Edge e1 = new Edge("a", "b");
        Edge e2 = new Edge("a", "b");

        Assert.assertEquals(e1, e2);
    }

    @Test
    public void differentElementsTest() {
        Edge e1 = new Edge("a", "b");
        Edge e2 = new Edge("a", "c");

        Assert.assertNotEquals(e1, e2);
    }
}
