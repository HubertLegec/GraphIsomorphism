package com.pw.eiti.graphisomorphism.utils;

import com.pw.eiti.graphisomorphism.model.Graph;

import java.util.Map;

public class TestCaseDTO {
    Graph graphA;
    Graph graphB;
    Map<Integer, Integer> verticesMapping;

    public Graph getGraphA() {
        return graphA;
    }

    public void setGraphA(Graph graphA) {
        this.graphA = graphA;
    }

    public Graph getGraphB() {
        return graphB;
    }

    public void setGraphB(Graph graphB) {
        this.graphB = graphB;
    }

    public Map<Integer, Integer> getVerticesMapping() {
        return verticesMapping;
    }

    public void setVerticesMapping(Map<Integer, Integer> verticesMapping) {
        this.verticesMapping = verticesMapping;
    }
}
