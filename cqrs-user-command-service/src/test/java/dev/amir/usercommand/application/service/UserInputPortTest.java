package dev.amir.usercommand.application.service;

import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.usecase.UserUseCases;
import dev.amir.usercommand.domain.entity.User;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserInputPortTest {
    @Mock
    private UserOutputPort userOutputPort;
    @Mock
    private UserMessageOutputPort userMessageOutputPort;
    @InjectMocks
    private UserUseCases userUseCases;

    @Test
    void testCreateUser() {
        // Given
        User user = new User();
        user.setName("Amir");
        user.setLastname("Aranibar");
        user.setEmail("amir@test.com");
        user.setActive(true);

        User userResponse = new User();
        userResponse.setId(UUID.randomUUID().toString());
        when(userOutputPort.save(any(User.class))).thenReturn(userResponse);
        doNothing().when(userMessageOutputPort).sendMessage(any(User.class));

        // When
        String newUserId = userUseCases.createUser(user);

        // Then
        assertTrue(StringUtils.hasText(newUserId));
        verify(userOutputPort).save(any(User.class));
        verify(userMessageOutputPort).sendMessage(any(User.class));
    }

    @Test
    void testCreateUser_WithId_ShouldThrowException() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Amir");
        user.setLastname("Aranibar");
        user.setEmail("amir@test.com");
        user.setActive(true);

        var exception = assertThrows(IllegalArgumentException.class, () -> userUseCases.createUser(user));

        assertEquals("Invalid User, id field must be empty", exception.getMessage());
        verify(userOutputPort, never()).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("John");
        user.setLastname("Smith");
        user.setEmail("jsmith@test.com");
        user.setActive(true);

        when(userOutputPort.save(any(User.class))).thenReturn(user);
        doNothing().when(userMessageOutputPort).sendMessage(any(User.class));

        userUseCases.updateUser(user);

        verify(userOutputPort).save(any(User.class));
        verify(userMessageOutputPort).sendMessage(any(User.class));
    }

    @Test
    void testUpdateUser_ShouldThrowException() {
        User user = new User();
        user.setName("John");
        user.setLastname("Smith");
        user.setEmail("jsmith@test.com");
        user.setActive(true);

        var exception = assertThrows(IllegalArgumentException.class, () -> userUseCases.updateUser(user));

        assertEquals("Invalid User, id field must exist", exception.getMessage());
        verify(userOutputPort, never()).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        String userId = UUID.randomUUID().toString();

        when(userOutputPort.delete(eq(userId))).thenReturn(true);

        boolean isUserDeleted = userUseCases.deleteUser(userId);

        assertTrue(isUserDeleted);
        verify(userOutputPort).delete(eq(userId));
    }

    @Test
    void testDeleteUser_ShouldThrowException() {
        var exception = assertThrows(IllegalArgumentException.class, () -> userUseCases.deleteUser(null));

        assertEquals("Invalid User, id field must exist", exception.getMessage());
        verify(userOutputPort, never()).delete(anyString());
    }
}
