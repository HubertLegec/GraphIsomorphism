package com.pw.eiti.graphisomorphism.checker.preconditions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.google.common.collect.Lists;

public class GraphIsomorphismPreconditionCollectionTest {

	@Test
	public void testFullfils_whenAllPreconditionsFullfiled_success() throws Exception {
		// given
		final GraphIsomorphismPreconditionCollection preconditionCollection = new GraphIsomorphismPreconditionCollection(Lists.newArrayList(
				getMockPrecondition(true),
				getMockPrecondition(true),
				getMockPrecondition(true),
						getMockPrecondition(true)
				));
		// when
		final boolean fullfils = preconditionCollection.fulfills(null, null);
		// then
		assertThat(fullfils).isTrue();
	}

	@Test
	public void testFullfils_whenNotAllPreconditionsFullfiled_failure() throws Exception {
		// given
		final GraphIsomorphismPreconditionCollection preconditionCollection = new GraphIsomorphismPreconditionCollection(
				Lists.newArrayList(
				getMockPrecondition(true),
				getMockPrecondition(true),
						getMockPrecondition(false), getMockPrecondition(true)
				));
		// when
		final boolean fullfils = preconditionCollection.fulfills(null, null);
		// then
		assertThat(fullfils).isFalse();
	}

	private Precondition getMockPrecondition(final boolean isFullfiled) {
		final Precondition mockPrecond = mock(Precondition.class);
		when(mockPrecond.fulfills(any(), any())).thenReturn(isFullfiled);
		return mockPrecond;
	}

}
