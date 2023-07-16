package dev.amir.usercommand.application.service;

import dev.amir.usercommand.application.port.input.UserUseCases;
import dev.amir.usercommand.application.port.output.UserOutputPort;
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
    private UserOutputPort userOutputPort;
    @InjectMocks
    private UserUseCases userService;

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

        // When
        String newUserId = userService.createUser(user);

        // Then
        assertTrue(StringUtils.hasText(newUserId));
        verify(userOutputPort).save(any(User.class));
    }

    @Test
    void testCreateUser_WithId_ShouldThrowException() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Amir");
        user.setLastname("Aranibar");
        user.setEmail("amir@test.com");
        user.setActive(true);

        var exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));

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

        userService.updateUser(user);

        verify(userOutputPort).save(any(User.class));
    }

    @Test
    void testUpdateUser_ShouldThrowException() {
        User user = new User();
        user.setName("John");
        user.setLastname("Smith");
        user.setEmail("jsmith@test.com");
        user.setActive(true);

        var exception = assertThrows(IllegalArgumentException.class, () -> userService.updateUser(user));

        assertEquals("Invalid User, id field must exist", exception.getMessage());
        verify(userOutputPort, never()).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        String userId = UUID.randomUUID().toString();

        when(userOutputPort.delete(eq(userId))).thenReturn(true);

        boolean isUserDeleted = userService.deleteUser(userId);

        assertTrue(isUserDeleted);
        verify(userOutputPort).delete(eq(userId));
    }

    @Test
    void testDeleteUser_ShouldThrowException() {
        var exception = assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(null));

        assertEquals("Invalid User, id field must exist", exception.getMessage());
        verify(userOutputPort, never()).delete(anyString());
    }
}
