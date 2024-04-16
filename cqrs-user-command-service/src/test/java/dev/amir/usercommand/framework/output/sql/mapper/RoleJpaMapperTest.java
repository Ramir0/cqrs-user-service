package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.Role;
import dev.amir.usercommand.framework.output.sql.entity.RoleJpa;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PermissionJpaMapperImpl.class, RoleJpaMapperImpl.class})
class RoleJpaMapperTest {

    @Autowired
    private RoleJpaMapper underTest;

    @Test
    void test_Convert_FromRoleJpa_ToRole() {
        RoleJpa expected = RandomObject.nextObject(RoleJpa.class);

        Role actual = underTest.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPermissions().size(), actual.getPermissions().size());
    }

    @Test
    void test_Convert_WhenRoleJpaIsNull_ReturnsNull() {
        Role actual = underTest.convert(null);

        assertNull(actual);
    }

    @Test
    void test_Convert_FromRoleJpa_ToRole_WithNoPermissions() {
        RoleJpa expected = RandomObject.nextObject(RoleJpa.class);
        expected.setPermissions(null);

        Role actual = underTest.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertNull(actual.getPermissions());
    }
}
