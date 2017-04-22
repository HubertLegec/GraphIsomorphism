package com.pw.eiti.graphisomorphism.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class TestCaseLoader {
    private static final Logger log = Logger.getLogger(TestCaseLoader.class);

    private TestCaseLoader() {}

    public static List<Object[]> loadTestCasesFromDirectory(Path directory) throws IOException {
        return Files.walk(directory)
                .filter(Files::isRegularFile)
                .peek(path -> log.info("Test file found: " + path))
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
}
