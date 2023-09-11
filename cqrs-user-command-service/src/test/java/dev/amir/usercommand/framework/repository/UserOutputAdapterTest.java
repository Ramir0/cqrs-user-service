package dev.amir.usercommand.framework.repository;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.sql.adapter.UserOutputAdapter;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserOutputAdapterTest {
    @Mock
    private UserJpaMapper jpaMapperMock;
    @Mock
    private UserJpaRepository jpaRepositoryMock;
    @InjectMocks
    private UserOutputAdapter userRepository;

    @Test
    void test_save_WithValidData() {
        User user = new User();
        user.setName("Amir");
        UserJpa userJpa = new UserJpa();
        userJpa.setName("Amir");

        String savedUserId = UUID.randomUUID().toString();
        User savedUser = new User();
        savedUser.setId(savedUserId);
        UserJpa savedUserJpa = new UserJpa();
        savedUserJpa.setId(savedUserId);

        when(jpaMapperMock.convert(eq(user))).thenReturn(userJpa);
        when(jpaRepositoryMock.save(userJpa)).thenReturn(savedUserJpa);
        when(jpaMapperMock.convert(eq(savedUserJpa))).thenReturn(savedUser);

        User actualSavedUser = userRepository.save(user);

        assertEquals(savedUser.getId(), actualSavedUser.getId());
        verify(jpaMapperMock).convert(eq(user));
        verify(jpaRepositoryMock).save(userJpa);
        verify(jpaMapperMock).convert(eq(savedUserJpa));
    }
}
