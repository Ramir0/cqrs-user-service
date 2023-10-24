package dev.amir.usercommand.framework.output.sql.adapter;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import dev.amir.usercommand.framework.output.sql.mapper.UserJpaMapper;
import dev.amir.usercommand.framework.output.sql.repository.UserJpaRepository;
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
        user.setName("Name");
        UserJpa userJpa = new UserJpa();
        userJpa.setName("Name");

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
    void test_Update_WhenUserExist_ThrowsException() {
        UserId newUserId = new UserId();
        User newUser = new User();
        newUser.setId(newUserId);
        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(false);


        assertThrows(
                UserNotFoundException.class,
                () -> underTest.update(newUser)
        );

        verify(jpaRepositoryMock).existsById(eq(newUserId.getValue()));
        verify(jpaRepositoryMock, never()).save(any());
    }

    @Test
    void test_Update() {
        UserId savedUserId = new UserId();
        User user = new User();
        user.setName("Name");
        user.setId(savedUserId);
        UserJpa userJpa = new UserJpa();
        userJpa.setName("Name");

        User savedUser = new User();
        savedUser.setId(savedUserId);
        UserJpa savedUserJpa = new UserJpa();
        savedUserJpa.setId(savedUserId.getValue());

        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(true);
        when(jpaMapperMock.convert(eq(user))).thenReturn(userJpa);
        when(jpaRepositoryMock.save(userJpa)).thenReturn(savedUserJpa);
        when(jpaMapperMock.convert(eq(savedUserJpa))).thenReturn(savedUser);

        User actualSavedUser = underTest.update(user);

        assertEquals(savedUser.getId(), actualSavedUser.getId());
        verify(jpaRepositoryMock).existsById(eq(savedUserId.getValue()));
        verify(jpaMapperMock).convert(eq(user));
        verify(jpaRepositoryMock).save(userJpa);
        verify(jpaMapperMock).convert(eq(savedUserJpa));
    }

    @Test
    void test_Delete() {
        UserId userId = new UserId();
        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(true);
        doNothing().when(jpaRepositoryMock).deleteById(any(UUID.class));

        boolean actual = underTest.delete(userId);

        assertTrue(actual);
        verify(jpaRepositoryMock).existsById(eq(userId.getValue()));
        verify(jpaRepositoryMock).deleteById(eq(userId.getValue()));
    }

    @Test
    void test_Delete_WhenUserDoesNotExist_ReturnsFalse() {
        UserId userId = new UserId();
        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(false);

        boolean actual = underTest.delete(userId);

        assertFalse(actual);
        verify(jpaRepositoryMock).existsById(eq(userId.getValue()));
        verify(jpaRepositoryMock, never()).deleteById(any());
    }
}