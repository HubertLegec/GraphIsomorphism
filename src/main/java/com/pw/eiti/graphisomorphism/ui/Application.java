package com.pw.eiti.graphisomorphism.ui;

import java.io.IOException;
import java.util.List;

import com.pw.eiti.graphisomorphism.model.Graph;

public class Application {
	public static void main(final String[] args) {
		if (args.length == 0 || !args[0].endsWith(".json")) {
			System.out.println("Specify path to file with graphs in json format as parameter");
		}
		final String path = args[0];
		try {
			final List<Graph> graphs = InputFileParser.parse(path);
			//TODO - process graphs
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}