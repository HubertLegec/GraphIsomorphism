package com.pw.eiti.graphisomorphism.checker.vertexmatcher;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Class representing a matching of one graph's vertices to other
 * graph's vertices.
 */
public class VertexMatching {
	private int matchedVarticesCount;
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
	public VertexMatching(final int verticesCount) {
		this.allVerticesCount = verticesCount;
		this.matchedVarticesCount = 0;
		this.srcToDstMatch = new Integer[this.allVerticesCount];
		this.dstToSrcMatch = new Integer[this.allVerticesCount];
		for(int i = 0; i < this.allVerticesCount; ++i) {
			this.srcToDstMatch[i] = null;
			this.dstToSrcMatch[i] = null;
		}
	}

	/**
	 * has all vertices been matched?
	 */
	public boolean isComplete() {
		return allVerticesCount == matchedVarticesCount;
	}

	/**
	 * Adds a mapping between vertices
	 *
	 * @param vSrc source graph vertex
	 * @param vDst destination graph vertex
	 */
	public void add(final int vSrc, final int vDst) {
		if(srcToDstMatch[vSrc] == null) {
			this.matchedVarticesCount++;
		}
		srcToDstMatch[vSrc] = vDst;
		dstToSrcMatch[vDst] = vSrc;
	}

	/**
	 * Removes mapping for soruce graph vertex
	 *
	 * @param vSrc source graph vertex
	 */
	public void remove(final int vSrc) {
		final Integer vDst = srcToDstMatch[vSrc];
		if(vDst != null) {
			this.matchedVarticesCount--;
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
	public Integer getDstBySrc(final int vSrc) {
		return srcToDstMatch[vSrc];
	}

	/**
	 * Get source graph vertex which is matched with given
	 * destination graph vertex.
	 *
	 * @param vSrc destination graph vertex for which source
	 * 		graph vertex will be returned
	 * @return source graph vertex matched with given
	 * 		destination graph vertex
	 */
	public Integer getSrcByDst(final int vDst) {
		return dstToSrcMatch[vDst];
	}

	/**
	 * @return count of vertices currently matched
	 */
	public int getMatchedVerticesCount() {
		return this.matchedVarticesCount;
	}

	public Map<Integer, Integer> getMatching() {
		final HashMap<Integer, Integer> matching = Maps.newHashMap();
		for(int i = 0; i < allVerticesCount; ++i) {
			matching.put(i, srcToDstMatch[i]);
		}
		return matching;
	}
}