package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.framework.input.rest.mapper.UserRequestMapper;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.DeleteUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import dev.amir.usercommand.framework.input.rest.response.CreateUserResponse;
import dev.amir.usercommand.util.RandomObject;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserHandlerTest {
    @Mock
    private UserInputPort userInputPortMock;
    @Mock
    private UserRequestMapper requestMapperMock;

    @InjectMocks
    private UserHandlerImpl underTest;

    @Test
    void test_Handle_WhenInputIsCreateUserRequest() {
        UserId userId = new UserId();
        CreateUserRequest request = RandomObject.nextObject(CreateUserRequest.class);
        User user = new User();
        when(requestMapperMock.convert(any(CreateUserRequest.class))).thenReturn(user);
        when(userInputPortMock.createUser(any(User.class))).thenReturn(userId);

        CreateUserResponse actual = underTest.handle(request);

        assertNotNull(actual);
        assertEquals(userId.toString(), actual.id());
        verify(requestMapperMock).convert(eq(request));
        verify(userInputPortMock).createUser(eq(user));
    }

    @Test
    void test_Handle_WhenInputIsUpdateUserRequest() {
        UserId userId = RandomObject.nextObject(UserId.class);
        UpdateUserRequest request = RandomObject.nextObject(UpdateUserRequest.class);
        User user = new User();
        when(requestMapperMock.convert(any(UpdateUserRequest.class))).thenReturn(user);
        doNothing().when(userInputPortMock).updateUser(any(User.class));

        underTest.handle(request, userId);

        verify(requestMapperMock).convert(eq(request));
        verify(userInputPortMock).updateUser(eq(user));
    }

    @Test
    void test_Handle_WhenInputIsDeleteUserRequest() {
        UserId userId = RandomObject.nextObject(UserId.class);
        DeleteUserRequest request = RandomObject.nextObject(DeleteUserRequest.class);

        doNothing().when(userInputPortMock).deleteUser(any(UserId.class));

        underTest.handle(request, userId);

        verify(userInputPortMock).deleteUser(eq(userId));
    }

    @Test
    void test_Handle_WhenInputIsChangePasswordRequest() {
        UserId userId = RandomObject.nextObject(UserId.class);
        ChangePasswordRequest request = RandomObject.nextObject(ChangePasswordRequest.class);

        doNothing().when(userInputPortMock).changeUserPassword(any(UserId.class), any(UserPassword.class));

        underTest.handle(request, userId);

        verify(userInputPortMock).changeUserPassword(eq(userId), eq(request.password()));
    }
}
