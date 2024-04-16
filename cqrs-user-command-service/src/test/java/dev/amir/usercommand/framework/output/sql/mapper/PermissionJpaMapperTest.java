package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.Permission;
import dev.amir.usercommand.framework.output.sql.entity.PermissionJpa;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PermissionJpaMapperTest {

    private PermissionJpaMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(PermissionJpaMapper.class);
    }

    @Test
    void test_Convert_FromPermissionJpa_ToPermission() {
        PermissionJpa expected = RandomObject.nextObject(PermissionJpa.class);

        Permission actual = underTest.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void test_Convert_WhenPermissionJpaIsNull_ReturnsNull() {
        Permission actual = underTest.convert(null);

        assertNull(actual);
    }
}
