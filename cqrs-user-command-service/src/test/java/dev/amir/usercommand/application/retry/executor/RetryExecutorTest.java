package dev.amir.usercommand.application.retry.executor;

import dev.amir.usercommand.application.retry.action.RetryAction;
import dev.amir.usercommand.application.retry.function.RetryFunction;
import dev.amir.usercommand.domain.exception.UserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.retry.support.RetryTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void test_Execute_ReturnsCallbackResponse() {
        Integer expected = 100;
        RetryFunction<Integer> callback = () -> expected;
        when(retryTemplateMock.execute(any())).thenReturn(expected);

        Integer actual = underTest.execute(callback);

        assertEquals(expected, actual);
        verify(retryTemplateMock).execute(any());
    }

    @Test
    void test_Execute_WhenCallbackThrowsUserException() {
        RetryFunction<Integer> callback = () -> 100; // review lamda
        when(retryTemplateMock.execute(any())).thenThrow(new UserException("Something went wrong"));

        assertThrows(UserException.class, () -> underTest.execute(callback));

        verify(retryTemplateMock).execute(any());
    }

    @Test
    void test_Execute_WhenCallbackThrowsException() {
        RetryFunction<Integer> callback = () -> 100;
        when(retryTemplateMock.execute(any())).thenThrow(new Exception("Something went wrong"));

        assertThrows(Exception.class, () -> underTest.execute(callback));
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
    void test_Execute_WhenCallbackThrowsUserException_DoesNothing() {
        RetryAction callback = () -> {
        };
        when(retryTemplateMock.execute(any())).thenThrow(UserException.class);

        assertThrows(UserException.class, () -> underTest.execute(callback));
        verify(retryTemplateMock).execute(any());
    }

    @Test
    void test_Execute_WhenCallbackThrowsException_DoesNothing() {
        RetryAction callback = () -> {
        };
        when(retryTemplateMock.execute(any())).thenThrow(Exception.class);

        assertThrows(Exception.class, () -> underTest.execute(callback));

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
