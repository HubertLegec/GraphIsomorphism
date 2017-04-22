package com.pw.eiti.graphisomorphism.generator;

import org.jgrapht.graph.DefaultEdge;

public class IntEdge extends DefaultEdge {

    Integer getV1() {
        return ((IntVertex)super.getSource()).value;
    }

    Integer getV2() {
        return ((IntVertex)super.getTarget()).value;
    }
}
