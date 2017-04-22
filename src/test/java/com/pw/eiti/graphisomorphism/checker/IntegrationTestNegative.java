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
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class IntegrationTestNegative extends IntegrationTestBase {

    public IntegrationTestNegative(Graph graphA, Graph graphB, Map<Integer, Integer> expectedResult) {
        super(graphA, graphB, expectedResult);
    }

    @Parameters
    public static Collection<Object[]> data() throws IOException, URISyntaxException {
        URL positiveDirURL = IntegrationTestPositive.class.getResource("/negative");
        Path positiveDirPath = Paths.get(positiveDirURL.toURI());
        assertTrue(Files.exists(positiveDirPath));
        return TestCaseLoader.loadTestCasesFromDirectory(positiveDirPath);
    }

    @Before
    public void setUp() {
        checker = GraphIsomorphismChecker.createDefaultChecker();
    }

    @Test
    public void checkIsomorphismNotPresent() {
        //when
        Optional<VertexMatching> result = checker.getIsomorphism(graphA, graphB);
        //then
        assertFalse(result.isPresent());
    }

}
