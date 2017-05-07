package com.pw.eiti.graphisomorphism.generator;

import org.jgrapht.graph.DefaultEdge;


/**
 * Class used by graph generator.
 * It extends {@link DefaultEdge} class to get start and end of edge as {@link Integer}
 */
public class IntEdge extends DefaultEdge {

    Integer getV1() {
        return ((IntVertex)super.getSource()).value;
    }

    Integer getV2() {
        return ((IntVertex)super.getTarget()).value;
    }
}
