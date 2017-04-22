package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

/**
 * Class representing a matching of one graph's vertices to other
 * graph's vertices.
 */
public class VertexMatching {
    private static final Logger log = Logger.getLogger(VertexMatching.class);
	private int matchedVerticesCount;
	private final int allVerticesCount;
	private final Integer[] srcToDstMatch;
	private final Integer[] dstToSrcMatch;

	/**
	 * Constructs object of this class for graphs with given
	 * count of vertices.
	 *
	 * @param verticesCount count of vertices for graphs
	 * which vertices matching will be described
	 */
	VertexMatching(final int verticesCount) {
		this.allVerticesCount = verticesCount;
		this.matchedVerticesCount = 0;
		this.srcToDstMatch = Collections.nCopies(this.allVerticesCount, null)
				.toArray(new Integer[0]);
		this.dstToSrcMatch = Collections.nCopies(this.allVerticesCount, null)
				.toArray(new Integer[0]);
	}

	/**
	 * has all vertices been matched?
	 */
	boolean isComplete() {
		return allVerticesCount == matchedVerticesCount;
	}

	/**
	 * Adds a mapping between vertices
	 *
	 * @param vSrc source graph vertex
	 * @param vDst destination graph vertex
	 */
	void add(final int vSrc, final int vDst) {
	    log.info("Add mapping: " + vSrc + " -> " + vDst);
		if(srcToDstMatch[vSrc] == null) {
			this.matchedVerticesCount++;
		}
		srcToDstMatch[vSrc] = vDst;
		dstToSrcMatch[vDst] = vSrc;
	}

	/**
	 * Removes mapping for source graph vertex
	 *
	 * @param vSrc source graph vertex
	 */
	void remove(final int vSrc) {
	    log.info("Remove mapping: " + vSrc);
		final Integer vDst = srcToDstMatch[vSrc];
		if(vDst != null) {
			this.matchedVerticesCount--;
			dstToSrcMatch[vDst] = null;
			srcToDstMatch[vSrc] = null;
		}
	}

	/**
	 * Get destination graph vertex which is matched with given
	 * source graph vertex.
	 *
	 * @param vSrc source graph vertex for which destination
	 * 		graph vertex will be returned
	 * @return destination graph vertex matched with given
	 * 		source graph vertex
	 */
	Integer getDstBySrc(final int vSrc) {
		return srcToDstMatch[vSrc];
	}

	/**
	 * Get source graph vertex which is matched with given
	 * destination graph vertex.
	 *
	 * @param vDst destination graph vertex for which source
	 * 		graph vertex will be returned
	 * @return source graph vertex matched with given
	 * 		destination graph vertex
	 */
	Integer getSrcByDst(final int vDst) {
		return dstToSrcMatch[vDst];
	}

	/**
	 * @return count of vertices currently matched
	 */
	int getMatchedVerticesCount() {
		return this.matchedVerticesCount;
	}

	public Map<Integer, Integer> getMatching() {
		return IntStream.range(0, allVerticesCount)
				.boxed()
				.collect(toMap(Function.identity(), this::getDstBySrc));
	}

	public String getSrcToDstMatchString() {
	    return IntStream.range(0, srcToDstMatch.length)
                .boxed()
                .map(idx -> idx + " -> " + srcToDstMatch[idx] + "\n")
                .reduce("", (a, b) -> a + b);
    }
}