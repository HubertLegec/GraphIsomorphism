package com.pw.eiti.graphisomorphism.generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pw.eiti.graphisomorphism.model.Edge;
import com.pw.eiti.graphisomorphism.model.Graph;
import org.jgrapht.VertexFactory;
import org.jgrapht.generate.GnpRandomGraphGenerator;
import org.jgrapht.graph.ClassBasedVertexFactory;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * This class contains methods used to generate random test cases of any size.
 * It also has method to save generated test case to json file
 */
public class TestGraphGenerator {

    /**
     * There is no need to create instance of this class
     */
    private TestGraphGenerator() {}

    /**
     * Generates random test case with given params.
     * Test case consist of two graphs of the same vertices and edges count.
     * The only difference between them is that labels of vertices in GraphB are permutation of labels from GraphA
     * @param verticesCount Expected graph size
     * @param edgeProbability Probability of edge occurrence between two vertices
     * @return Generated test case
     */
    public static TestCaseDTO generateTestCase(int verticesCount, double edgeProbability) {
        Graph graphA = generateGraph(verticesCount, edgeProbability);
        graphA.setName("GraphA");
        Map<Integer, Integer> verticesPermutation = getVerticesPermutation(graphA.getVertices());
        Graph graphB = new Graph();
        graphB.setName("GraphB");
        graphB.setVerticesCount(verticesCount);
        graphB.setEdges(
                graphA.getEdges()
                        .stream()
                        .map(e -> new Edge(verticesPermutation.get(e.getV1()), verticesPermutation.get(e.getV2())))
                        .collect(toList())
        );
        TestCaseDTO dto = new TestCaseDTO();
        dto.setGraphA(graphA);
        dto.setGraphB(graphB);
        dto.setVerticesMapping(verticesPermutation);
        return dto;
    }

    private static Graph generateGraph(int verticesCount, double edgeProbability) {
        GnpRandomGraphGenerator<IntVertex, IntEdge> generator = new GnpRandomGraphGenerator<>(verticesCount, edgeProbability);
        VertexFactory<IntVertex> factory = new ClassBasedVertexFactory<>(IntVertex.class);
        org.jgrapht.Graph<IntVertex, IntEdge> graph = new DefaultDirectedGraph<>(IntEdge.class);
        generator.generateGraph(graph, factory, null);
        Graph result = new Graph();
        result.setVerticesCount(verticesCount);
        List<Edge> edges = graph.edgeSet().stream()
                .map(e -> new Edge(e.getV1(), e.getV2()))
                .collect(toList());
        result.setEdges(edges);
        return result;
    }

    private static Map<Integer, Integer> getVerticesPermutation(List<Integer> vertices) {
        List<Integer> verticesCopy = new ArrayList<>(vertices);
        Collections.shuffle(verticesCopy);
        return IntStream.range(0, vertices.size())
                .boxed()
                .collect(toMap(
                        vertices::get,
                        verticesCopy::get
                ));
    }

    /**
     * Saves given test case object to json file
     * @param dto test case to save
     * @param path path to file
     * @throws IOException when file creating fails
     */
    public static void saveTestCaseToFile(TestCaseDTO dto, String path) throws IOException {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            gson.toJson(dto, writer);
        }
    }

}
