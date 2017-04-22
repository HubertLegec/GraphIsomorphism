package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;
import org.apache.log4j.Logger;

public class EdgesCountPrecondition implements Precondition {
	private static final Logger log = Logger.getLogger(EdgesCountPrecondition.class);

	@Override
	public boolean fulfills(final Graph a, final Graph b) {
		boolean result = a.getEdges().size() == b.getEdges().size();
		log.info("fulfills: " + result);
		return result;
	}
}