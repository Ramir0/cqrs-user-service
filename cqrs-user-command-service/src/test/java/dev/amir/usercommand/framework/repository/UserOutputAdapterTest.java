package dev.amir.usercommand.framework.repository;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import dev.amir.usercommand.framework.output.sql.mapper.UserJpaMapper;
import dev.amir.usercommand.framework.output.sql.repository.UserJpaRepository;
import dev.amir.usercommand.framework.output.sql.adapter.UserOutputAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserOutputAdapterTest {
    @Mock
    private UserJpaMapper jpaMapper;
    @Mock
    private UserJpaRepository jpaRepository;
    @InjectMocks
    private UserOutputAdapter userRepository;

    @Test
    void save_WithValidData() {
        User user = new User();
        user.setName("Amir");
        User savedUser = new User();
        savedUser.setId(UUID.randomUUID().toString());

        UserJpa userJpa = new UserJpa();
        userJpa.setName("Amir");
        UserJpa savedUserJpa = new UserJpa();
        savedUserJpa.setId(UUID.randomUUID().toString());

        when(jpaMapper.convert(eq(user))).thenReturn(userJpa);
        when(jpaRepository.save(userJpa)).thenReturn(savedUserJpa);
        when(jpaMapper.convert(eq(savedUserJpa))).thenReturn(savedUser);

        User actualSavedUser = userRepository.save(user);

        assertEquals(savedUser.getId(), actualSavedUser.getId());
        verify(jpaMapper).convert(eq(user));
        verify(jpaRepository).save(userJpa);
        verify(jpaMapper).convert(eq(savedUserJpa));
    }
}