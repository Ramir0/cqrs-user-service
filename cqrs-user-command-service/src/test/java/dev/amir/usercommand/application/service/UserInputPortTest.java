package dev.amir.usercommand.application.service;

import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.usecase.UserUseCases;
import dev.amir.usercommand.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserInputPortTest {
    @Mock
    private UserOutputPort userOutputPortMock;
    @Mock
    private UserMessageOutputPort userMessageOutputPortMock;
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

        User userResponse = new User();
        userResponse.setId(UUID.randomUUID().toString());
        when(userOutputPortMock.save(any(User.class))).thenReturn(userResponse);
        doNothing().when(userMessageOutputPortMock).sendMessage(any(User.class));

        // When
        String newUserId = userUseCases.createUser(user);

        // Then
        assertTrue(StringUtils.hasText(newUserId));
        verify(userOutputPortMock).save(any(User.class));
        verify(userMessageOutputPortMock).sendMessage(any(User.class));
    }

    @Test
    void test_CreateUser_WithId_ShouldThrowException() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Amir");
        user.setLastname("Aranibar");
        user.setEmail("amir@test.com");
        user.setActive(true);

        var exception = assertThrows(IllegalArgumentException.class, () -> userUseCases.createUser(user));

        assertEquals("Invalid User, id field must be empty", exception.getMessage());
        verify(userOutputPortMock, never()).save(any(User.class));
    }

    @Test
    void test_UpdateUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("John");
        user.setLastname("Smith");
        user.setEmail("jsmith@test.com");
        user.setActive(true);

        when(userOutputPortMock.save(any(User.class))).thenReturn(user);
        doNothing().when(userMessageOutputPortMock).sendMessage(any(User.class));

        userUseCases.updateUser(user);

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

        var exception = assertThrows(IllegalArgumentException.class, () -> userUseCases.updateUser(user));

        assertEquals("Invalid User, id field must exist", exception.getMessage());
        verify(userOutputPortMock, never()).save(any(User.class));
    }

    @Test
    void test_DeleteUser() {
        String userId = UUID.randomUUID().toString();

        when(userOutputPortMock.delete(eq(userId))).thenReturn(true);

        boolean isUserDeleted = userUseCases.deleteUser(userId);

        assertTrue(isUserDeleted);
        verify(userOutputPortMock).delete(eq(userId));
    }

    @Test
    void test_DeleteUser_ShouldThrowException() {
        var exception = assertThrows(IllegalArgumentException.class, () -> userUseCases.deleteUser(null));

        assertEquals("Invalid User, id field must exist", exception.getMessage());
        verify(userOutputPortMock, never()).delete(anyString());
    }
}
