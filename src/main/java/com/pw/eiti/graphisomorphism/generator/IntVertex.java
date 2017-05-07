package com.pw.eiti.graphisomorphism.generator;


/**
 * Class used by graph generator.
 * Generator requires no params constructor and each vertex must be unique.
 * Static counter field is used to give each vertex in generated graph unique identifier.
 * {@code IntVertex.resetCounter()} method should be called before graph generating.
 */
public class IntVertex {
    private static int counter = 0;

    public int value;

    public  IntVertex() {
        value = counter++;
    }

    public static void resetCounter() {
        counter = 0;
    }
}