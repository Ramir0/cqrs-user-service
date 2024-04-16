package dev.amir.usercommand.framework.output.sql.adapter;

import dev.amir.usercommand.domain.entity.Role;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.framework.output.sql.entity.RoleJpa;
import dev.amir.usercommand.framework.output.sql.mapper.RoleJpaMapper;
import dev.amir.usercommand.framework.output.sql.repository.RoleJpaRepository;
import dev.amir.usercommand.util.RandomObject;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleOutputAdapterTest {
    @Mock
    private RoleJpaMapper jpaMapperMock;
    @Mock
    private RoleJpaRepository jpaRepositoryMock;
    @InjectMocks
    private RoleOutputAdapter underTest;

    @Test
    void test_GetRole_ShouldReturnRoleInstance() {
        RoleId roleId = RandomObject.nextObject(RoleId.class);
        RoleJpa roleJpa = RandomObject.nextObject(RoleJpa.class);
        Role expected = RandomObject.nextObject(Role.class);

        when(jpaRepositoryMock.findById(any(RoleId.class))).thenReturn(Optional.of(roleJpa));
        when(jpaMapperMock.convert(any(RoleJpa.class))).thenReturn(expected);

        Role result = underTest.getRole(roleId);

        assertEquals(expected, result);
        verify(jpaRepositoryMock).findById(eq(roleId));
        verify(jpaMapperMock).convert(eq(roleJpa));
    }

    @Test
    void test_GetRole_WhenRoleJpaDoesNotExist_ShouldThrowNotFoundException() {
        RoleId roleId = RandomObject.nextObject(RoleId.class);

        when(jpaRepositoryMock.findById(any(RoleId.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> underTest.getRole(roleId));

        verify(jpaRepositoryMock).findById(eq(roleId));
        verify(jpaMapperMock, never()).convert(any());
    }
}
