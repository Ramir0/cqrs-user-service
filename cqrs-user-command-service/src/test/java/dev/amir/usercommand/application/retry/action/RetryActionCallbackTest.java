package dev.amir.usercommand.application.retry.action;

import org.junit.jupiter.api.Test;
import org.springframework.retry.RetryContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RetryActionCallbackTest {

    @Test
    void test_DoWithRetry() {
        RetryAction action = () -> {
        };
        RetryContext contextMock = mock(RetryContext.class);
        when(contextMock.getRetryCount()).thenReturn(0);
        RetryActionCallback<Exception> underTest = new RetryActionCallback<>(action);

        underTest.doWithRetry(contextMock);

        verify(contextMock).getRetryCount();
        verify(contextMock, never()).getLastThrowable();
    }

    @Test
    void test_DoWithRetry_WhenRetryCountIsGreaterThanZero_LogsWarnWithThrowable() {
        RetryAction action = () -> {
        };
        RetryContext contextMock = mock(RetryContext.class);
        when(contextMock.getRetryCount()).thenReturn(1);
        when(contextMock.getLastThrowable()).thenReturn(new NullPointerException("Test Message"));
        RetryActionCallback<Exception> underTest = new RetryActionCallback<>(action);

        underTest.doWithRetry(contextMock);

        verify(contextMock, times(2)).getRetryCount();
        verify(contextMock).getLastThrowable();
    }
}
