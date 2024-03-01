package dev.amir.usercommand.framework.input.rest.controller;

import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.input.rest.handler.UserHandler;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserHandler userHandlerMock;

    @InjectMocks
    private UserController underTest;

    @Test
    void test_CreateUser() {
        CreateUserRequest request = RandomObject.nextObject(CreateUserRequest.class);
        CreateUserResponse response = new CreateUserResponse(UUID.randomUUID().toString());
        when(userHandlerMock.handle(any(CreateUserRequest.class))).thenReturn(response);

        ResponseEntity<CreateUserResponse> actual = underTest.createUser(request);

        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
        assertEquals(response, actual.getBody());
    }

    @Test
    void test_UpdateUser() {
        UserId userId = RandomObject.nextObject(UserId.class);
        UpdateUserRequest request = RandomObject.nextObject(UpdateUserRequest.class);
        doNothing().when(userHandlerMock).handle(any(UpdateUserRequest.class), any(UserId.class));

        ResponseEntity<Void> actual = underTest.updateUser(userId, request);

        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        assertNull(actual.getBody());
        verify(userHandlerMock).handle(eq(request), eq(userId));
    }

    @Test
    void test_DeleteUser() {
        UserId userId = RandomObject.nextObject(UserId.class);

        doNothing().when(userHandlerMock).handle(any(DeleteUserRequest.class), any(UserId.class));

        ResponseEntity<Void> actual = underTest.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        verify(userHandlerMock).handle(any(DeleteUserRequest.class), eq(userId));
    }

    @Test
    void test_ChangePassword() {
        UserId userId = RandomObject.nextObject(UserId.class);
        ChangePasswordRequest request = RandomObject.nextObject(ChangePasswordRequest.class);
        doNothing().when(userHandlerMock).handle(any(ChangePasswordRequest.class), any(UserId.class));

        ResponseEntity<Void> actual = underTest.changePassword(userId, request);

        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        assertNull(actual.getBody());
        verify(userHandlerMock).handle(eq(request), eq(userId));
    }
}
