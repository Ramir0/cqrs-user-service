package dev.amir.userquery.framework.output.sql.adapter;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.sql.entity.UserJpa;
import dev.amir.userquery.framework.output.sql.mapper.UserEntityMapper;
import dev.amir.userquery.framework.output.sql.repository.UserJpaRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserOutputAdapterTest {

    @Mock
    private UserJpaRepository userJpaRepositoryMock;
    @Mock
    private UserEntityMapper userEntityMapperMock;

    @InjectMocks
    private UserOutputAdapter underTest;

    @Test
    public void test_GetAll() {
        UserJpa userJpa = new UserJpa();
        User expectedUser = new User();
        when(userJpaRepositoryMock.findAll()).thenReturn(List.of(userJpa));
        when(userEntityMapperMock.convert(any(UserJpa.class))).thenReturn(expectedUser);

        Collection<User> actual = underTest.getAll();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        verify(userJpaRepositoryMock).findAll();
        verify(userEntityMapperMock).convert(eq(userJpa));
    }

    @Test
    public void test_GetById() {
        String userId = UUID.randomUUID().toString();
        UserJpa userJpa = new UserJpa();
        User expectedUser = new User();
        when(userJpaRepositoryMock.findById(anyString())).thenReturn(Optional.of(userJpa));
        when(userEntityMapperMock.convert(any(UserJpa.class))).thenReturn(expectedUser);

        Optional<User> actual = underTest.getById(userId);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        verify(userJpaRepositoryMock).findById(eq(userId));
        verify(userEntityMapperMock).convert(eq(userJpa));
    }
}
