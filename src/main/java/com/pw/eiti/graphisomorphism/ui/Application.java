package com.pw.eiti.graphisomorphism.ui;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.pw.eiti.graphisomorphism.checker.DegreeCalculator;
import com.pw.eiti.graphisomorphism.checker.GraphIsomorphismChecker;
import com.pw.eiti.graphisomorphism.checker.dfs.DFSPreOrderBuilder;
import com.pw.eiti.graphisomorphism.checker.dfs.VertexSorter;
import com.pw.eiti.graphisomorphism.checker.preconditions.DegreePrecondition;
import com.pw.eiti.graphisomorphism.checker.preconditions.EdgesCountPrecondition;
import com.pw.eiti.graphisomorphism.checker.preconditions.GraphIsomorphismPreconditionCollection;
import com.pw.eiti.graphisomorphism.checker.preconditions.Precondition;
import com.pw.eiti.graphisomorphism.checker.preconditions.VerticesCountPrecondition;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatchRequirementsFactory;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatcherFactory;
import com.pw.eiti.graphisomorphism.checker.vertexmatcher.VertexMatching;
import com.pw.eiti.graphisomorphism.model.Graph;

public class Application {
	public static void main(final String[] args) {
		if (args.length == 0 || !args[0].endsWith(".json")) {
			System.out.println("Specify path to file with graphs in json format as parameter");
			return;
		}
		final String path = args[0];
		try {
			final List<Graph> graphs = InputFileParser.parse(path);
			if(graphs.size() != 2) {
				System.out.println("Exactly two graphs should be specified");
				return;
			}
			final GraphIsomorphismChecker checker = setUpDependenciesAndGetChecker();
			final Optional<VertexMatching> isomorhism = checker.getIsomorhism(graphs.get(0), graphs.get(1));
			if(!isomorhism.isPresent()) {
				System.out.println("Graphs are not isomorphic");
				return;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private static GraphIsomorphismChecker setUpDependenciesAndGetChecker() {
		final DegreeCalculator degreCalc = new DegreeCalculator();
		final Precondition precondition =
				new GraphIsomorphismPreconditionCollection(Lists.newArrayList(
						new DegreePrecondition(degreCalc),
						new EdgesCountPrecondition(),
						new VerticesCountPrecondition()));
		final VertexSorter sorter = new VertexSorter(degreCalc);
		final DFSPreOrderBuilder dfsPreOrderBuilder = new DFSPreOrderBuilder(sorter);
		final VertexMatchRequirementsFactory requirementsFactory = new VertexMatchRequirementsFactory(dfsPreOrderBuilder);
		final VertexMatcherFactory vertexMatcherFactory = new VertexMatcherFactory(requirementsFactory);
		return new GraphIsomorphismChecker(precondition, vertexMatcherFactory);
	}
}