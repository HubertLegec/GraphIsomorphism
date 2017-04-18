package com.pw.eiti.graphisomorphism.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestFileParser {
    private static final Logger log = Logger.getLogger("TestFileParser");

    /**
     * There is no need to create instance of this class
     */
    private TestFileParser() {}

    public static Optional<TestCaseDTO> loadTestCaseFromFile(final String path) {
        try {
            final String jsonString = readFileAsString(path);
            final TestCaseDTO testCase = new Gson().fromJson(jsonString, TestCaseDTO.class);
            return Optional.of(testCase);
        } catch (IOException e) {
            log.log(Level.WARNING, "Can't open file: " + path);
            return Optional.empty();
        }
    }

    private static String readFileAsString(final String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
