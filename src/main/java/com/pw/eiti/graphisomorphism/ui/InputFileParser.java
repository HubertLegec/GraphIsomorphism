package com.pw.eiti.graphisomorphism.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.pw.eiti.graphisomorphism.model.Graph;

public class InputFileParser {

	public static List<Graph> parse(final String path) throws IOException, InvalidGraphException {
		final String jsonString = readFileAsString(path);
		final Graph[] graphs = new Gson().fromJson(jsonString, Graph[].class);
		return Arrays.asList(graphs);
	}

	private static String readFileAsString(final String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
