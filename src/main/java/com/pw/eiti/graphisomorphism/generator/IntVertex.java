package com.pw.eiti.graphisomorphism.generator;

public class IntVertex {
    private static int counter = 0;

    public int value;

    public  IntVertex() {
        value = counter++;
    }
}