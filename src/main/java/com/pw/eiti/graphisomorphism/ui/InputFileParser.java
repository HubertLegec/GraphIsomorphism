package com.pw.eiti.graphisomorphism.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.pw.eiti.graphisomorphism.model.Graph;

public class InputFileParser {

	public static List<Graph<String>> parse(final String path) throws IOException, InvalidGraphException {
		final String jsonString = readFileAsString(path);
		final Graph<String>[] graphs = new Gson().fromJson(jsonString, Graph[].class);
		Stream.of(graphs).forEach(Graph::validate);
		return Arrays.asList(graphs);
	}

	private static String readFileAsString(final String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
