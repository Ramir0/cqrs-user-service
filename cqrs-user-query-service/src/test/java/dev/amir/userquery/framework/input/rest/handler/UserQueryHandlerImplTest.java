package dev.amir.userquery.framework.input.rest.handler;

import dev.amir.userquery.application.port.input.UserInputPort;
import dev.amir.userquery.domain.entity.Role;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.input.rest.query.GetAllUsersQuery;
import dev.amir.userquery.framework.input.rest.query.GetUserByIdQuery;
import dev.amir.userquery.framework.input.rest.response.GetAllUsersResponse;
import dev.amir.userquery.framework.input.rest.response.GetUserByIdResponse;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserQueryHandlerImplTest {

    @Mock
    private UserInputPort userInputPortMock;

    @InjectMocks
    private UserQueryHandlerImpl underTest;

    @Test
    void test_Handle_GetAllUsersQuery() {
        Collection<User> expected = List.of(new User());
        GetAllUsersQuery query = new GetAllUsersQuery();
        when(userInputPortMock.getAllUsers()).thenReturn(expected);

        GetAllUsersResponse actual = underTest.handle(query);

        assertNotNull(actual);
        assertNotNull(actual.users());
        assertFalse(actual.users().isEmpty());
        verify(userInputPortMock).getAllUsers();
    }

    @Test
    void test_Handle_GetUserByIdQuery() {
        User expected = new User();
        String expectedUserId = UUID.randomUUID().toString();
        expected.setId(expectedUserId);
        expected.setRole(new Role());
        GetUserByIdQuery query = new GetUserByIdQuery(expectedUserId);
        when(userInputPortMock.getUserById(anyString())).thenReturn(expected);

        GetUserByIdResponse actual = underTest.handle(query);

        assertNotNull(actual);
        assertNotNull(actual);
        assertEquals(expectedUserId, actual.id());
        verify(userInputPortMock).getUserById(eq(expectedUserId));
    }
}
