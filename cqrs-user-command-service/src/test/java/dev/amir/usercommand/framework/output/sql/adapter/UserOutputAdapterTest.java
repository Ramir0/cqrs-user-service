package dev.amir.usercommand.framework.output.sql.adapter;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.UserEmail;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import dev.amir.usercommand.framework.output.sql.mapper.UserJpaMapper;
import dev.amir.usercommand.framework.output.sql.repository.UserJpaRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserOutputAdapterTest {
    @Mock
    private UserJpaMapper jpaMapperMock;
    @Mock
    private UserJpaRepository jpaRepositoryMock;
    @InjectMocks
    private UserOutputAdapter underTest;

    @Test
    void test_Save() {
        User user = new User();
        UserJpa userJpa = new UserJpa();

        UserId savedUserId = new UserId();
        User savedUser = new User();
        savedUser.setId(savedUserId);
        UserJpa savedUserJpa = new UserJpa();
        savedUserJpa.setId(savedUserId.getValue());

        when(jpaMapperMock.convert(eq(user))).thenReturn(userJpa);
        when(jpaRepositoryMock.save(userJpa)).thenReturn(savedUserJpa);
        when(jpaMapperMock.convert(eq(savedUserJpa))).thenReturn(savedUser);

        User actualSavedUser = underTest.save(user);

        assertEquals(savedUser.getId(), actualSavedUser.getId());
        verify(jpaMapperMock).convert(eq(user));
        verify(jpaRepositoryMock).save(userJpa);
        verify(jpaMapperMock).convert(eq(savedUserJpa));
    }

    @Test
    void test_Update_WhenUserDoesNotExist_ThrowsException() {
        UserId newUserId = new UserId();
        User newUser = new User();
        newUser.setId(newUserId);
        when(jpaRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());


        assertThrows(
                UserNotFoundException.class,
                () -> underTest.update(newUser)
        );

        verify(jpaRepositoryMock).findById(eq(newUserId.getValue()));
        verify(jpaRepositoryMock, never()).save(any());
    }

    @Test
    void test_Update() {
        UserId savedUserId = new UserId();
        User user = new User();
        user.setId(savedUserId);
        UserJpa userJpa = new UserJpa();

        User savedUser = new User();
        savedUser.setId(savedUserId);
        UserJpa savedUserJpa = new UserJpa();

        when(jpaRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.of(userJpa));
        when(jpaRepositoryMock.save(userJpa)).thenReturn(savedUserJpa);
        when(jpaMapperMock.convert(eq(savedUserJpa))).thenReturn(savedUser);

        User actualSavedUser = underTest.update(user);

        assertEquals(savedUser.getId(), actualSavedUser.getId());
        verify(jpaRepositoryMock).findById(eq(savedUserId.getValue()));
        verify(jpaRepositoryMock).save(userJpa);
        verify(jpaMapperMock).convert(eq(savedUserJpa));
    }

    @Test
    void test_Delete() {
        UserId userId = new UserId();

        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(true);
        doNothing().when(jpaRepositoryMock).deleteById(any(UUID.class));

        underTest.delete(userId);

        verify(jpaRepositoryMock).existsById(eq(userId.getValue()));
        verify(jpaRepositoryMock).deleteById(eq(userId.getValue()));
    }

    @Test
    void test_Delete_WhenUserDoesNotExist_ThrowsException() {
        UserId userId = new UserId();

        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> underTest.delete(userId));

        verify(jpaRepositoryMock).existsById(eq(userId.getValue()));
        verify(jpaRepositoryMock, never()).deleteById(any());
    }

    @Test
    void test_existByEmail_EmailExists_ReturnsTrue() {
        UserEmail email = new UserEmail("user_email@test.com");

        when(jpaRepositoryMock.existsByEmail(any(UserEmail.class))).thenReturn(true);

        boolean result = jpaRepositoryMock.existsByEmail(email);

        assertTrue(result);
        verify(jpaRepositoryMock).existsByEmail(email);
    }

    @Test
    void test_existByEmail_EmailDoesNotExist_ReturnsFalse() {
        UserEmail email = new UserEmail("user_email@test.com");

        when(jpaRepositoryMock.existsByEmail(any(UserEmail.class))).thenReturn(false);

        boolean result = jpaRepositoryMock.existsByEmail(email);

        assertFalse(result);
        verify(jpaRepositoryMock).existsByEmail(email);
    }

    @Test
    void test_existByUserName_UsernameExists_ReturnsTrue() {
        UserUsername userUsername = new UserUsername("Username");

        when(jpaRepositoryMock.existsByUsername(any(UserUsername.class))).thenReturn(true);

        boolean result = jpaRepositoryMock.existsByUsername(userUsername);

        assertTrue(result);
        verify(jpaRepositoryMock).existsByUsername(userUsername);
    }

    @Test
    void test_UsernameDoesNotExist_ReturnsFalse() {
        UserUsername userUsername = new UserUsername("Username");

        when(jpaRepositoryMock.existsByUsername(any(UserUsername.class))).thenReturn(false);

        boolean result = jpaRepositoryMock.existsByUsername(userUsername);

        assertFalse(result);
        verify(jpaRepositoryMock).existsByUsername(userUsername);
    }
}
