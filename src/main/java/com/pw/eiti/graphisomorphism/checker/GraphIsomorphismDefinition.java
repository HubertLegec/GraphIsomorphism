package com.pw.eiti.graphisomorphism.checker;

import java.util.Map;

import com.google.common.collect.Maps;
import com.pw.eiti.graphisomorphism.model.Graph;

/**
 * Class that defines the relation of isomorphism.
 */
public class GraphIsomorphismDefinition  {
	private final Graph first;
	private final Graph second;
	private final Map<Integer, Integer> firstToSecondMap = Maps.newHashMap();

	public GraphIsomorphismDefinition(final Graph first, final Graph second)
	{
		this.first = first;
		this.second = second;
	}
}