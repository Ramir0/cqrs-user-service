package dev.amir.userquery.framework.output.sql.adapter;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.sql.entity.UserEntity;
import dev.amir.userquery.framework.output.sql.mapper.UserEntityMapper;
import dev.amir.userquery.framework.output.sql.repository.UserRepository;
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
    private UserRepository userRepositoryMock;
    @Mock
    private UserEntityMapper userEntityMapperMock;

    @InjectMocks
    private UserOutputAdapter underTest;

    @Test
    public void test_GetAll() {
        UserEntity userEntity = new UserEntity();
        User expectedUser = new User();
        when(userRepositoryMock.findAll()).thenReturn(List.of(userEntity));
        when(userEntityMapperMock.convert(any(UserEntity.class))).thenReturn(expectedUser);

        Collection<User> actual = underTest.getAll();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        verify(userRepositoryMock).findAll();
        verify(userEntityMapperMock).convert(eq(userEntity));
    }

    @Test
    public void test_GetById() {
        String userId = UUID.randomUUID().toString();
        UserEntity userEntity = new UserEntity();
        User expectedUser = new User();
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(userEntity));
        when(userEntityMapperMock.convert(any(UserEntity.class))).thenReturn(expectedUser);

        Optional<User> actual = underTest.getById(userId);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        verify(userRepositoryMock).findById(eq(userId));
        verify(userEntityMapperMock).convert(eq(userEntity));
    }

    @Test
    void test_Save() {
        UserEntity userEntity = new UserEntity();
        User expectedUser = new User();
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userEntityMapperMock.convert(any(User.class))).thenReturn(userEntity);

        underTest.save(expectedUser);

        verify(userRepositoryMock).save(eq(userEntity));
        verify(userEntityMapperMock).convert(expectedUser);
    }
}
