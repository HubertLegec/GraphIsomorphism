package com.pw.eiti;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class InputFileParser {

    public static List<Graph> parse(String path) throws IOException, InvalidGraphException {
        String jsonString = readFileAsString(path);
        Graph[] graphs = new Gson().fromJson(jsonString, Graph[].class);
        Stream.of(graphs).forEach(Graph::validate);
        return Arrays.asList(graphs);
    }

    private static String readFileAsString(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
