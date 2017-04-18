package com.pw.eiti.graphisomorphism.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.pw.eiti.graphisomorphism.model.Graph;

class InputFileParser {

    /**
     * There is no need to create instance of this class
     */
    private InputFileParser() {
    }

    /**
     * Opens file with given path, parses its content and returns it as a list of {@link Graph} class instances.
     * Given file should contains a list of JSON representations of {@link Graph} class
     * @param path path to file with list of graphs in JSON format
     * @return list of graphs
     * @throws IOException when file doesn't exist or can't be opened
     * @throws InvalidGraphException when {@link Graph} JSON is invalid
     */
    static List<Graph> parse(final String path) throws IOException, InvalidGraphException {
		final String jsonString = readFileAsString(path);
		final Graph[] graphs = new Gson().fromJson(jsonString, Graph[].class);
		return Arrays.asList(graphs);
	}

	private static String readFileAsString(final String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
