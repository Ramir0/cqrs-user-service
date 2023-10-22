package dev.amir.usercommand.application.retry.executor;

import dev.amir.usercommand.application.retry.action.RetryAction;
import dev.amir.usercommand.application.retry.function.RetryFunction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.retry.support.RetryTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetryExecutorTest {
    @Mock
    private RetryTemplate retryTemplateMock;

    @InjectMocks
    private RetryExecutorImpl underTest;

    @Test
    void test_Execute_WithReturnType() {
        Integer expected = 100;
        RetryFunction<Integer> callback = () -> expected;
        when(retryTemplateMock.execute(any())).thenReturn(expected);

        Integer actual = underTest.execute(callback);

        assertEquals(expected, actual);
        verify(retryTemplateMock).execute(any());
    }

    @Test
    void test_Execute_WithReturnType_ReturnsNull() {
        RetryFunction<Integer> callback = () -> 100;
        when(retryTemplateMock.execute(any())).thenThrow(NullPointerException.class);

        Integer actual = underTest.execute(callback);

        assertNull(actual);
        verify(retryTemplateMock).execute(any());
    }

    @Test
    void test_Execute() {
        RetryAction callback = () -> {
        };
        when(retryTemplateMock.execute(any())).thenReturn(Void.TYPE);

        underTest.execute(callback);

        verify(retryTemplateMock).execute(any());
    }

    @Test
    void test_Execute_ReturnsNull() {
        RetryAction callback = () -> {
        };
        when(retryTemplateMock.execute(any())).thenThrow(NullPointerException.class);

        underTest.execute(callback);

        verify(retryTemplateMock).execute(any());
    }

    @Test
    void test_AsyncExecute() {
        RetryAction callback = () -> {
        };
        when(retryTemplateMock.execute(any())).thenReturn(Void.TYPE);

        underTest.asyncExecute(callback);

        verify(retryTemplateMock).execute(any());
    }
}
