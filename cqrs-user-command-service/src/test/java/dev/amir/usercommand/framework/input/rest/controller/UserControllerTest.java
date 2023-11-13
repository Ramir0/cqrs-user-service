package dev.amir.usercommand.framework.input.rest.controller;

import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.framework.input.rest.handler.UserHandler;
import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.DeleteUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import dev.amir.usercommand.framework.input.rest.response.CreateUserResponse;
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
        CreateUserRequest request = new CreateUserRequest(
                "Name",
                "Lastname",
                "Email",
                UserStatus.ACTIVE
        );
        CreateUserResponse response = new CreateUserResponse(UUID.randomUUID().toString());
        when(userHandlerMock.handle(any(CreateUserRequest.class))).thenReturn(response);

        ResponseEntity<CreateUserResponse> actual = underTest.createUser(request);

        assertEquals(HttpStatus.CREATED, actual.getStatusCode());
        assertEquals(response, actual.getBody());
    }

    @Test
    void test_UpdateUser() {
        UUID userId = UUID.randomUUID();
        UpdateUserRequest request = new UpdateUserRequest(
                "Name",
                "Lastname",
                "Email",
                UserStatus.ACTIVE
        );
        doNothing().when(userHandlerMock).handle(any(UpdateUserRequest.class), any(UUID.class));

        ResponseEntity<Void> actual = underTest.updateUser(userId, request);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNull(actual.getBody());
        verify(userHandlerMock).handle(eq(request), eq(userId));
    }

    @Test
    void test_DeleteUser() {
        UUID userId = UUID.randomUUID();

        doNothing().when(userHandlerMock).handle(any(DeleteUserRequest.class), any(UUID.class));

        ResponseEntity<Void> actual = underTest.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        verify(userHandlerMock).handle(any(DeleteUserRequest.class), eq(userId));
    }
}
