package dev.amir.usercommand.application.retry.function;

import org.junit.jupiter.api.Test;
import org.springframework.retry.RetryContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RetryFunctionCallbackTest {

    @Test
    void test_DoWithRetry() {
        Integer expected = 100;
        RetryFunction<Integer> function = () -> expected;
        RetryContext contextMock = mock(RetryContext.class);
        when(contextMock.getRetryCount()).thenReturn(0);
        RetryFunctionCallback<Integer, Exception> underTest = new RetryFunctionCallback<>(function);

        Integer actual = underTest.doWithRetry(contextMock);

        assertEquals(expected, actual);
        verify(contextMock).getRetryCount();
        verify(contextMock, never()).getLastThrowable();
    }

    @Test
    void test_DoWithRetry_WhenRetryCountIsGreaterThanZero_LogsWarnWithThrowable() {
        Integer expected = 100;
        RetryFunction<Integer> function = () -> expected;
        RetryContext contextMock = mock(RetryContext.class);
        when(contextMock.getRetryCount()).thenReturn(1);
        when(contextMock.getLastThrowable()).thenReturn(new NullPointerException("Test Message"));
        RetryFunctionCallback<Integer, Exception> underTest = new RetryFunctionCallback<>(function);

        Integer actual = underTest.doWithRetry(contextMock);

        assertEquals(expected, actual);
        verify(contextMock, times(2)).getRetryCount();
        verify(contextMock).getLastThrowable();
    }
}
