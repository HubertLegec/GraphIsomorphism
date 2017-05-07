package com.pw.eiti.graphisomorphism.ui;

import com.pw.eiti.graphisomorphism.checker.GraphIsomorphismChecker;
import com.pw.eiti.graphisomorphism.generator.TestGraphGenerator;

import java.time.Instant;
import java.util.stream.IntStream;


/**
 * This class allow to measure average execution time for graph with given number of vertices and edge probability.
 * It takes 3 execution arguments:
 * 1 - number of random samples used to designate average execution time
 * 2 - size of sample graph
 * 3 - edge probability in each sample graph
 */
public class AverageExecutionTimeChecker {
    public static void main(String[] args) {
        final int attempts = Integer.parseInt(args[0]);
        final int size = Integer.parseInt(args[1]);
        final double probability = Double.parseDouble(args[2]);
        double avgTimeInSeconds = IntStream.range(0, attempts)
                .boxed()
                .map(i -> TestGraphGenerator.generateTestCase(size, probability))
                .map(dto -> {
                    GraphIsomorphismChecker checker = GraphIsomorphismChecker.createDefaultChecker();
                    Instant start = Instant.now();
                    checker.getIsomorphism(dto.getGraphA(), dto.getGraphB());
                    Instant end = Instant.now();
                    return (end.toEpochMilli() - start.toEpochMilli()) / 1000.0;
                })
                .reduce(0.0, (a, b) -> a + b);
        System.out.println("Execution time [s]: " + avgTimeInSeconds / attempts);

    }
}
