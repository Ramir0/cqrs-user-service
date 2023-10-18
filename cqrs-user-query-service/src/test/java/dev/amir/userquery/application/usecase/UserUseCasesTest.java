package dev.amir.userquery.application.usecase;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.domain.exception.UserException;
import dev.amir.userquery.domain.exception.UserNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCasesTest {

    @Mock
    private UserOutputPort userOutputPortMock;

    @InjectMocks
    private UserUseCases underTest;

    @Test
    void test_GetAllUsers() {
        when(userOutputPortMock.getAll()).thenReturn(List.of(new User()));

        Collection<User> actual = underTest.getAllUsers();

        assertFalse(actual.isEmpty());
        verify(userOutputPortMock).getAll();
    }

    @Test
    void test_GetUserById() {
        String expectedUserId = UUID.randomUUID().toString();
        User expected = new User();
        expected.setId(expectedUserId);
        when(userOutputPortMock.getById(anyString())).thenReturn(Optional.of(expected));

        User actual = underTest.getUserById(expectedUserId);

        assertNotNull(actual);
        verify(userOutputPortMock).getById(eq(expectedUserId));
    }

    @Test
    void test_GetAllUsers_WhenSomethingWentWrong_ThrowsException() {
        when(userOutputPortMock.getAll()).thenThrow(NullPointerException.class);

        assertThrows(
                UserException.class,
                () -> underTest.getAllUsers()
        );
        verify(userOutputPortMock).getAll();
    }

    @Test
    void test_GetUserById_WhenUserNotFound_ThrowsException() {
        String expectedUserId = UUID.randomUUID().toString();
        when(userOutputPortMock.getById(anyString())).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> underTest.getUserById(expectedUserId)
        );
        verify(userOutputPortMock).getById(eq(expectedUserId));
    }
}
