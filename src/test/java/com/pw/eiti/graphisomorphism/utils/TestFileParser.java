package com.pw.eiti.graphisomorphism.utils;

import com.google.gson.Gson;
import com.pw.eiti.graphisomorphism.generator.TestCaseDTO;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

class TestFileParser {
    private static final Logger log = Logger.getLogger(TestFileParser.class);

    /**
     * There is no need to create instance of this class
     */
    private TestFileParser() {}

    static Optional<TestCaseDTO> loadTestCaseFromFile(final String path) {
        log.info("Load test case from file: " + path);
        try {
            final String jsonString = readFileAsString(path);
            final TestCaseDTO testCase = new Gson().fromJson(jsonString, TestCaseDTO.class);
            return Optional.of(testCase);
        } catch (IOException e) {
            log.error( "Can't open file: " + path);
            return Optional.empty();
        }
    }

    private static String readFileAsString(final String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
