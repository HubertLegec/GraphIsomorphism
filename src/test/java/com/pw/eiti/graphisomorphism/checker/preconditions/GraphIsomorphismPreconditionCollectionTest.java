package com.pw.eiti.graphisomorphism.checker.preconditions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import java.util.Arrays;

public class GraphIsomorphismPreconditionCollectionTest {

    @Test
    public void testFulfills_whenAllPreconditionsFulfilled_success() {
        // given
        final GraphIsomorphismPreconditionCollection preconditionCollection =
                new GraphIsomorphismPreconditionCollection(
                        Arrays.asList(
                                getMockedPrecondition(true),
                                getMockedPrecondition(true),
                                getMockedPrecondition(true),
                                getMockedPrecondition(true)
                        ));
        // when
        final boolean fulfills = preconditionCollection.fulfills(null, null);
        // then
        assertThat(fulfills).isTrue();
    }

    @Test
    public void testFulfills_whenNotAllPreconditionsFulfilled_failure() {
        // given
        final GraphIsomorphismPreconditionCollection preconditionCollection =
                new GraphIsomorphismPreconditionCollection(
                        Arrays.asList(
                                getMockedPrecondition(true),
                                getMockedPrecondition(true),
                                getMockedPrecondition(false),
                                getMockedPrecondition(true)
                        ));
        // when
        final boolean fulfills = preconditionCollection.fulfills(null, null);
        // then
        assertThat(fulfills).isFalse();
    }

    private static Precondition getMockedPrecondition(final boolean isFulfilled) {
        final Precondition mockPrecondition = mock(Precondition.class);
        when(mockPrecondition.fulfills(any(), any())).thenReturn(isFulfilled);
        return mockPrecondition;
    }

}
