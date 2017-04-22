package com.pw.eiti.graphisomorphism.checker;

import com.pw.eiti.graphisomorphism.model.Graph;

import java.util.Map;

public class IntegrationTestBase {
    protected GraphIsomorphismChecker checker;
    protected Graph graphA;
    protected Graph graphB;
    protected Map<Integer, Integer> expectedResult;

    public IntegrationTestBase(Graph graphA, Graph graphB, Map<Integer, Integer> expectedResult) {
        this.graphA = graphA;
        this.graphB = graphB;
        this.expectedResult = expectedResult;
    }
}
