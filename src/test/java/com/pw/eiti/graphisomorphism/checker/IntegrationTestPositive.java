package com.pw.eiti.graphisomorphism.checker;

import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatching;
import com.pw.eiti.graphisomorphism.model.Graph;
import com.pw.eiti.graphisomorphism.utils.TestCaseLoader;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class IntegrationTestPositive extends IntegrationTestBase {

    public IntegrationTestPositive(Graph graphA, Graph graphB, Map<Integer, Integer> expectedResult) {
        super(graphA, graphB, expectedResult);
    }

    @Parameters
    public static Collection<Object[]> data() throws IOException, URISyntaxException {
        URL positiveDirURL = IntegrationTestPositive.class.getResource("/positive");
        Path positiveDirPath = Paths.get(positiveDirURL.toURI());
        assertTrue(Files.exists(positiveDirPath));
        return TestCaseLoader.loadTestCasesFromDirectory(positiveDirPath);
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
