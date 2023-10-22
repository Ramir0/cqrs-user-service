package dev.amir.usercommand.application.usecase;

import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.retry.RetryFunctionMatcher;
import dev.amir.usercommand.application.retry.action.RetryAction;
import dev.amir.usercommand.application.retry.executor.RetryExecutor;
import dev.amir.usercommand.application.retry.function.RetryFunction;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserUseCasesTest {

    @Captor
    ArgumentCaptor<RetryFunction<User>> retryFunctionCaptor;
    @Captor
    ArgumentCaptor<RetryAction> retryActionCaptor;

    @Mock
    private UserOutputPort userOutputPortMock;
    @Mock
    private UserMessageOutputPort userMessageOutputPortMock;
    @Mock
    private RetryExecutor retryExecutorMock;
    @InjectMocks
    private UserUseCases underTest;

    @Test
    void test_CreateUser() {
        // Given
        User user = new User();
        user.setName("Amir");
        user.setLastname("Aranibar");
        user.setEmail("amir@test.com");
        user.setActive(true);
        RetryFunctionMatcher<User> retryMatcher = new RetryFunctionMatcher<>();

        User userResponse = new User();
        userResponse.setId(new UserId());

        when(retryExecutorMock.execute(argThat(retryMatcher))).thenReturn(userResponse);
        doNothing().when(retryExecutorMock).asyncExecute(any(RetryAction.class));
        when(userOutputPortMock.save(any(User.class))).thenReturn(userResponse);
        doNothing().when(userMessageOutputPortMock).sendMessage(any(User.class));

        // When
        UserId newUserId = underTest.createUser(user);

        // Then
        assertNotNull(newUserId);

        verify(retryExecutorMock).execute(retryFunctionCaptor.capture());
        RetryFunction<User> retryFunction = retryFunctionCaptor.getValue();
        retryFunction.execute();
        verify(userOutputPortMock).save(eq(user));

        verify(retryExecutorMock).asyncExecute(retryActionCaptor.capture());
        RetryAction retryAction = retryActionCaptor.getValue();
        retryAction.execute();
        verify(userMessageOutputPortMock).sendMessage(eq(userResponse));
    }

    @Test
    void test_UpdateUser() {
        User user = new User();
        user.setId(new UserId());
        user.setName("John");
        user.setLastname("Smith");
        user.setEmail("jsmith@test.com");
        user.setActive(true);

        when(userOutputPortMock.update(any(User.class))).thenReturn(user);
        doNothing().when(userMessageOutputPortMock).sendMessage(any(User.class));

        underTest.updateUser(user);

        verify(userOutputPortMock).update(eq(user));
        verify(userMessageOutputPortMock).sendMessage(eq(user));
    }

    @Test
    void test_DeleteUser() {
        UserId userId = new UserId();

        when(userOutputPortMock.delete(any())).thenReturn(true);

        boolean isUserDeleted = underTest.deleteUser(userId.getValue());

        assertTrue(isUserDeleted);
        verify(userOutputPortMock).delete(eq(userId));
    }
}
