package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

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
		if(srcToDstMatch[vSrc] == null) {
			this.matchedVerticesCount++;
		}
		srcToDstMatch[vSrc] = vDst;
		dstToSrcMatch[vDst] = vSrc;
	}

	/**
	 * Removes mapping for soruce graph vertex
	 *
	 * @param vSrc source graph vertex
	 */
	void remove(final int vSrc) {
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
				.collect(toMap(Function.identity(), i -> srcToDstMatch[i]));
	}
}