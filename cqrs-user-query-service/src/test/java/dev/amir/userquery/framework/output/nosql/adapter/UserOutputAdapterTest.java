package dev.amir.userquery.framework.output.nosql.adapter;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.nosql.entity.UserMongo;
import dev.amir.userquery.framework.output.nosql.mapper.UserMongoMapper;
import dev.amir.userquery.framework.output.nosql.repository.UserMongoRepository;
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
class UserOutputAdapterTest {
    @Mock
    private UserMongoRepository userMongoRepositoryMock;
    @Mock
    private UserMongoMapper userMongoMapperMock;

    @InjectMocks
    private UserOutputAdapter underTest;


    @Test
    void test_GetAll() {
        UserMongo userMongo = new UserMongo();
        User expectedUser = new User();
        when(userMongoRepositoryMock.findAll()).thenReturn(List.of(userMongo));
        when(userMongoMapperMock.convert(any(UserMongo.class))).thenReturn(expectedUser);

        Collection<User> actual = underTest.getAll();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        verify(userMongoRepositoryMock).findAll();
        verify(userMongoMapperMock).convert(eq(userMongo));
    }

    @Test
    void test_GetById() {
        String userId = UUID.randomUUID().toString();
        UserMongo userMongo = new UserMongo();
        User expectedUser = new User();
        when(userMongoRepositoryMock.findById(anyString())).thenReturn(Optional.of(userMongo));
        when(userMongoMapperMock.convert(any(UserMongo.class))).thenReturn(expectedUser);

        Optional<User> actual = underTest.getById(userId);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        verify(userMongoRepositoryMock).findById(eq(userId));
        verify(userMongoMapperMock).convert(eq(userMongo));
    }

    @Test
    void test_Save() {
        UserMongo userMongo = new UserMongo();
        User expectedUser = new User();
        when(userMongoRepositoryMock.save(any(UserMongo.class))).thenReturn(userMongo);
        when(userMongoMapperMock.convert(any(User.class))).thenReturn(userMongo);


        underTest.save(expectedUser);

        verify(userMongoRepositoryMock).save(eq(userMongo));
        verify(userMongoMapperMock).convert(expectedUser);
    }
}
