package dev.amir.usercommand.application.usecase;

import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.retry.RetryFunctionMatcher;
import dev.amir.usercommand.application.retry.action.RetryAction;
import dev.amir.usercommand.application.retry.executor.RetryExecutor;
import dev.amir.usercommand.application.retry.function.RetryFunction;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.exception.DuplicateUserException;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.Email;
import dev.amir.usercommand.domain.valueobject.user.Password;
import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.domain.valueobject.user.Username;
import dev.amir.usercommand.util.RandomObject;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
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
    private RetryExecutor retryExecutorMock;
    @InjectMocks
    private UserUseCases underTest;

    @Test
    void test_CreateUser() {
        // Given
        RetryFunctionMatcher<User> retryMatcher = new RetryFunctionMatcher<>();

        User userResponse = new User();
        userResponse.setId(new UserId());
        User user = RandomObject.nextObject(User.class);

        when(retryExecutorMock.execute(argThat(retryMatcher))).thenReturn(userResponse);
        when(userOutputPortMock.save(any(User.class))).thenReturn(userResponse);

        UserId invalidUserId = user.getId();
        RoleId invalidRoleId = user.getRoleId();
        LocalDateTime invalidCreatedAt = user.getCreatedAt();

        // When
        UserId newUserId = underTest.createUser(user);

        // Then
        assertNotEquals(invalidUserId, newUserId);
        assertNotEquals(invalidRoleId, user.getRoleId());
        assertNotEquals(invalidCreatedAt, user.getCreatedAt());

        verify(retryExecutorMock).execute(retryFunctionCaptor.capture());
        RetryFunction<User> retryFunction = retryFunctionCaptor.getValue();
        retryFunction.execute();
        verify(userOutputPortMock).save(eq(user));
    }

    @Test
    void test_when_EmailExists_ThrowsException() {
        User user = new User();
        user.setEmail(new Email("user_email@test.com"));

        when(userOutputPortMock.existByEmail(any(User.class))).thenReturn(true);

        DuplicateUserException exception = assertThrows(
                DuplicateUserException.class,
                () -> underTest.createUser(user)
        );

        assertEquals("User with email: " + user.getEmail() + " already exists", exception.getMessage());
        verify(userOutputPortMock).existByEmail(user);
    }

    @Test
    void test_when_UserUserNameExists_ThrowsException() {
        User user = new User();
        user.setUsername(new Username("user_name"));

        when(userOutputPortMock.existsByUsername(any(User.class))).thenReturn(true);

        DuplicateUserException exception = assertThrows(
                DuplicateUserException.class,
                () -> underTest.createUser(user)
        );

        assertEquals("User with username : " + user.getUsername() + " already exists", exception.getMessage());
        verify(userOutputPortMock, times(1)).existsByUsername(user);
    }

    @Test
    void test_ChangePassword() {
        User userResponse = new User();
        UserId userId = new UserId();
        userResponse.setId(userId);
        Password password = RandomObject.nextObject(Password.class);

        doNothing().when(retryExecutorMock).execute(any(RetryAction.class));
        doNothing().when(userOutputPortMock).changePassword(any(UserId.class), any(Password.class));

        underTest.changeUserPassword(userId, password);

        verify(retryExecutorMock).execute(retryActionCaptor.capture());
        RetryAction retryAction = retryActionCaptor.getValue();
        retryAction.execute();
        verify(userOutputPortMock).changePassword(eq(userId), eq(password));
    }

    @Test
    void test_DeleteUser() {
        doNothing().when(retryExecutorMock).execute(any(RetryAction.class));
        doNothing().when(userOutputPortMock).delete(any(UserId.class));

        UserId userId = new UserId();
        underTest.deleteUser(userId);

        verify(retryExecutorMock).execute(retryActionCaptor.capture());
        RetryAction retryAction = retryActionCaptor.getValue();
        retryAction.execute();
        verify(userOutputPortMock).delete(eq(userId));
    }

    @Test
    void test_UpdateUser() {
        User user = RandomObject.nextObject(User.class);
        RetryFunctionMatcher<User> retryMatcher = new RetryFunctionMatcher<>();

        User userResponse = new User();
        userResponse.setId(new UserId());

        when(retryExecutorMock.execute(argThat(retryMatcher))).thenReturn(userResponse);
        when(userOutputPortMock.update(any(User.class))).thenReturn(user);

        underTest.updateUser(user);

        verify(retryExecutorMock).execute(retryFunctionCaptor.capture());
        RetryFunction<User> retryFunction = retryFunctionCaptor.getValue();
        retryFunction.execute();
        verify(userOutputPortMock).update(eq(user));
    }

    @Test
    void test_when_UserIsRemoved_ThrowsException() {
        UserId userId = new UserId();
        Password password = RandomObject.nextObject(Password.class);

        when(userOutputPortMock.isUserRemoved(any(UserId.class))).thenReturn(true);

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> underTest.changeUserPassword(userId, password)
        );

        assertEquals("User with ID: " + userId + " Not found", exception.getMessage());
        verify(userOutputPortMock).isUserRemoved(eq(userId));
    }

    @Test
    void updateUserProfile() {
        User user = RandomObject.nextObject(User.class);

        doNothing().when(retryExecutorMock).execute(any(RetryAction.class));
        doNothing().when(userOutputPortMock).updateProfile(any(User.class));

        underTest.updateUserProfile(user);

        verify(retryExecutorMock).execute(retryActionCaptor.capture());
        RetryAction retryFunction = retryActionCaptor.getValue();
        retryFunction.execute();
        verify(userOutputPortMock).updateProfile(eq(user));
    }
}
