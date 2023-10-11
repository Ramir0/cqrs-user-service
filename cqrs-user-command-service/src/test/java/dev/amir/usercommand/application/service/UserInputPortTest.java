package dev.amir.usercommand.application.service;

import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.retry.RetryAction;
import dev.amir.usercommand.application.retry.RetryExecutor;
import dev.amir.usercommand.application.retry.RetryFunctionMatcher;
import dev.amir.usercommand.application.usecase.UserUseCases;
import dev.amir.usercommand.application.validation.Validator;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserInputPortTest {
    @Mock
    private UserOutputPort userOutputPortMock;
    @Mock
    private UserMessageOutputPort userMessageOutputPortMock;
    @Mock
    private RetryExecutor retryExecutorMock;
    @Mock
    private Validator validatorMock;
    @InjectMocks
    private UserUseCases userUseCases;

    @Test
    void test_CreateUser() {
        // Given
        User user = new User();
        user.setName("Amir");
        user.setLastname("Aranibar");
        user.setEmail("amir@test.com");
        user.setActive(true);
        RetryFunctionMatcher<User> retryMatcher = new RetryFunctionMatcher<>();
        doNothing().when(validatorMock).validate(any());

        User userResponse = new User();
        userResponse.setId(new UserId());
        when(retryExecutorMock.execute(argThat(retryMatcher))).thenReturn(userResponse);
        doNothing().when(retryExecutorMock).asyncExecute(any(RetryAction.class));

        // When
        UserId newUserId = userUseCases.createUser(user);

        // Then
        assertNotNull(newUserId);
        verify(validatorMock).validate(any(User.class));
        verify(userOutputPortMock, never()).save(any(User.class));
        verify(userMessageOutputPortMock, never()).sendMessage(any(User.class));
        verify(retryExecutorMock).execute(argThat(retryMatcher));
        verify(retryExecutorMock).asyncExecute(any(RetryAction.class));
    }

    @Test
    void test_CreateUser_WithId_ShouldThrowException() {
        User user = new User();
        user.setId(new UserId());
        user.setName("Amir");
        user.setLastname("Aranibar");
        user.setEmail("amir@test.com");
        user.setActive(true);
        doThrow(ConstraintViolationException.class).when(validatorMock).validate(any());

        assertThrows(ConstraintViolationException.class, () -> userUseCases.createUser(user));

        verify(validatorMock).validate(any(User.class));
        verify(userOutputPortMock, never()).save(any(User.class));
    }

    @Test
    void test_UpdateUser() {
        User user = new User();
        user.setId(new UserId());
        user.setName("John");
        user.setLastname("Smith");
        user.setEmail("jsmith@test.com");
        user.setActive(true);
        doNothing().when(validatorMock).validate(any());

        when(userOutputPortMock.save(any(User.class))).thenReturn(user);
        doNothing().when(userMessageOutputPortMock).sendMessage(any(User.class));

        userUseCases.updateUser(user);

        verify(validatorMock).validate(any(User.class));
        verify(userOutputPortMock).save(any(User.class));
        verify(userMessageOutputPortMock).sendMessage(any(User.class));
    }

    @Test
    void test_UpdateUser_ShouldThrowException() {
        User user = new User();
        user.setName("John");
        user.setLastname("Smith");
        user.setEmail("jsmith@test.com");
        user.setActive(true);
        doThrow(ConstraintViolationException.class).when(validatorMock).validate(any());

        assertThrows(ConstraintViolationException.class, () -> userUseCases.updateUser(user));
        verify(validatorMock).validate(any(User.class));
        verify(userOutputPortMock, never()).save(any(User.class));
    }

    @Test
    void test_DeleteUser() {
        UserId userId = new UserId();
        doNothing().when(validatorMock).validate(any());

        when(userOutputPortMock.delete(any())).thenReturn(true);

        boolean isUserDeleted = userUseCases.deleteUser(userId.getValue());

        assertTrue(isUserDeleted);
        verify(validatorMock).validate(any(UserId.class));
        verify(userOutputPortMock).delete(eq(userId));
    }

    @Test
    void test_DeleteUser_ShouldThrowException() {
        doThrow(ConstraintViolationException.class).when(validatorMock).validate(any());

        assertThrows(ConstraintViolationException.class, () -> userUseCases.deleteUser(null));
        verify(validatorMock).validate(any(UserId.class));
        verify(userOutputPortMock, never()).delete(any());
    }
}
