package com.pw.eiti.graphisomorphism.checker;

import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatching;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.utils.TestFileParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class IntegrationTest {
    private GraphIsomorphismChecker checker;
    private Graph graphA;
    private Graph graphB;
    private Map<Integer, Integer> expectedResult;

    @Parameters
    public static Collection<Object[]> data() throws IOException, URISyntaxException {
        URL positiveDirURL = IntegrationTest.class.getResource("/positive");
        Path positiveDirPath = Paths.get(positiveDirURL.toURI());
        assertTrue(Files.exists(positiveDirPath));
        return Files.walk(positiveDirPath)
                .filter(Files::isRegularFile)
                .map(path -> TestFileParser.loadTestCaseFromFile(path.toString()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .peek(dto -> {
                    dto.getGraphA().reload();
                    dto.getGraphB().reload();
                })
                .map(dto -> Arrays.asList(dto.getGraphA(), dto.getGraphB(), dto.getVerticesMapping()).toArray())
                .collect(toList());
    }

    public IntegrationTest(Graph graphA, Graph graphB, Map<Integer, Integer> expectedResult) {
        this.graphA = graphA;
        this.graphB = graphB;
        this.expectedResult = expectedResult;
    }

    @Before
    public void setUp() {
        checker = GraphIsomorphismChecker.createDefaultChecker();
    }

    @Test
    public void checkIsomorphism() {
        //when
        Optional<VertexMatching> result = checker.getIsomorphism(graphA, graphB);
        //then
        assertTrue(result.isPresent());
        Map<Integer, Integer> resultMap = result.get().getMatching();
        assertEquals(expectedResult.size(), resultMap.size());
        assertThat(resultMap.entrySet())
                .containsAll(expectedResult.entrySet());
    }
}
