package com.pw.eiti.graphisomorphism.ui;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.pw.eiti.graphisomorphism.checker.GraphIsomorphismChecker;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatching;
import com.pw.eiti.graphisomorphism.model.Graph;
import org.apache.log4j.Logger;


/**
 * Main entry point of application.
 * It requires execution parameter with path to file with graphs in JSON format.
 * If isomorphism is present, it prints vertices mapping on console,
 * otherwise information that graphs are not isomorphic is returned.
 */
public class Application {
	private static final Logger log = Logger.getLogger(Application.class);

	public static void main(final String[] args) {
		if (args.length == 0 || !args[0].endsWith(".json")) {
			log.error("Specify path to file with graphs in json format as parameter");
			return;
		}
		final String path = args[0];
        final Optional<List<Graph>> graphs = InputFileParser.parse(path);
        graphs.ifPresent(Application::checkIsomorphism);
	}

	private static void checkIsomorphism(List<Graph> graphs) {
        log.info("First graph vertices count: " + graphs.get(0).getVerticesCount());
        log.info("Second graph vertices count: " + graphs.get(1).getVerticesCount());
        final GraphIsomorphismChecker checker = GraphIsomorphismChecker.createDefaultChecker();
        Instant start = Instant.now();
        final Optional<VertexMatching> isomorphism = checker.getIsomorphism(graphs.get(0), graphs.get(1));
        Instant end = Instant.now();
        log.info("Execution time: " + Duration.between(start, end));
        if(!isomorphism.isPresent()) {
            log.info("Graphs are not isomorphic");
        } else {
            log.info("Number of vertices in each graph: " + graphs.get(0).getVerticesCount());
            log.info("Number of edges in each graph: " + graphs.get(0).getEdges().size());
            printIsomorphism(isomorphism.get());
        }
    }

	private static void printIsomorphism(VertexMatching matching) {
	    log.info("Graphs are isomorphic:\n" + matching.getSrcToDstMatchString());
    }
}