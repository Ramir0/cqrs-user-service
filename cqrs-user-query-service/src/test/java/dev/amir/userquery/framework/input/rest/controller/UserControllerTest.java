package dev.amir.userquery.framework.input.rest.controller;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.input.rest.handler.UserQueryHandler;
import dev.amir.userquery.framework.input.rest.query.GetAllUsersQuery;
import dev.amir.userquery.framework.input.rest.query.GetUserByIdQuery;
import dev.amir.userquery.framework.input.rest.response.GetAllUsersResponse;
import dev.amir.userquery.framework.input.rest.response.GetUserByIdResponse;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserQueryHandler userQueryHandlerMock;

    @InjectMocks
    private UserController underTest;

    @Test
    void test_GetAllUsers() {
        GetAllUsersResponse response = new GetAllUsersResponse(List.of(new User()));
        when(userQueryHandlerMock.handle(any(GetAllUsersQuery.class))).thenReturn(response);

        ResponseEntity<GetAllUsersResponse> actual = underTest.getAllUsers();

        assertNotNull(actual);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
        assertFalse(actual.getBody().users().isEmpty());
        verify(userQueryHandlerMock).handle(any(GetAllUsersQuery.class));
    }

    @Test
    void test_GetUserById() {
        String userId = UUID.randomUUID().toString();
        GetUserByIdQuery query = new GetUserByIdQuery(userId);
        GetUserByIdResponse response = new GetUserByIdResponse(new User());
        when(userQueryHandlerMock.handle(any(GetUserByIdQuery.class))).thenReturn(response);

        ResponseEntity<GetUserByIdResponse> actual = underTest.getUserById(query);

        assertNotNull(actual);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
        assertNotNull(actual.getBody().user());
        verify(userQueryHandlerMock).handle(eq(query));
    }
}
