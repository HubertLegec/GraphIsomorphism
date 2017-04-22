package com.pw.eiti.graphisomorphism.checker.preconditions;

import com.pw.eiti.graphisomorphism.model.Graph;
import org.apache.log4j.Logger;

public class VerticesCountPrecondition implements Precondition {
	private static final Logger log = Logger.getLogger(VerticesCountPrecondition.class);

	@Override
	public boolean fulfills(final Graph a, final Graph b) {
        boolean result = a.getVerticesCount() == b.getVerticesCount();
        log.info("fulfills: " + result);
        return result;
	}
}