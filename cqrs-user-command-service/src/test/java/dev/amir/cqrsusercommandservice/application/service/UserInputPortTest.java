package dev.amir.cqrsusercommandservice.application.service;

import dev.amir.cqrsusercommandservice.application.port.input.UserInputAdapter;
import dev.amir.cqrsusercommandservice.application.port.output.UserOutputPort;
import dev.amir.cqrsusercommandservice.domain.entity.User;
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
    private UserInputAdapter userService;

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
}
