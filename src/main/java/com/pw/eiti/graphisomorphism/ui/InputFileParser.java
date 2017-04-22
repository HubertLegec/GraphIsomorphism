package com.pw.eiti.graphisomorphism.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pw.eiti.graphisomorphism.model.Graph;
import org.apache.log4j.Logger;

import static java.util.stream.Collectors.toList;

class InputFileParser {
    private static final Logger log = Logger.getLogger(InputFileParser.class);

    /**
     * There is no need to create instance of this class
     */
    private InputFileParser() {
    }

    /**
     * Opens file with given path, parses its content and returns it as a list of {@link Graph} class instances.
     * Given file should contains a list of JSON representations of {@link Graph} class
     *
     * @param path path to file with list of graphs in JSON format
     * @return {@link Optional} with list of graphs if exactly two graphs was found in file, {@code Optional.empty()} otherwise
     */
    static Optional<List<Graph>> parse(final String path) {
        log.info("Parse input file: " + path);
        try {
            final String jsonString = readFileAsString(path);
            final Graph[] graphs = new Gson().fromJson(jsonString, Graph[].class);
            if(graphs.length != 2) {
                log.error("Exactly two graphs should be specified");
                return Optional.empty();
            }
            List<Graph> graphList = Stream.of(graphs)
                    .peek(Graph::reload)
                    .collect(toList());
            return Optional.of(graphList);
        } catch (IOException e) {
            log.error("Can't open file: " + path);
            return Optional.empty();
        } catch (JsonSyntaxException e) {
            log.error("Invalid json format:\n" + e.getMessage());
            return Optional.empty();
        }
	}

	private static String readFileAsString(final String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
