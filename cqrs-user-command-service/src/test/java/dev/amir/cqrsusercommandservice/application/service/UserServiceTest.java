package dev.amir.cqrsusercommandservice.application.service;

import dev.amir.cqrsusercommandservice.application.repository.UserRepository;
import dev.amir.cqrsusercommandservice.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testCreateUser() {
        // Given
        User user = User.builder()
                .name("Amir")
                .lastname("Aranibar")
                .email("amir@test.com")
                .isActive(true)
                .build();
        when(userRepository.save(any(User.class))).thenReturn(UUID.randomUUID().toString());

        // When
        String newUserId = userService.createUser(user);

        // Then
        assertTrue(StringUtils.hasText(newUserId));
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testCreateUser_WithId_ShouldThrowException() {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name("Amir")
                .lastname("Aranibar")
                .email("amir@test.com")
                .isActive(true)
                .build();

        var exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));

        assertEquals("Invalid User, id field must be empty", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}
