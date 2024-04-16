package dev.amir.usercommand.framework.output.sql.type;

import dev.amir.usercommand.domain.valueobject.permission.PermissionId;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PermissionIdTypeTest {
    private PermissionIdType underTest;

    @BeforeEach
    void setUp() {
        underTest = new PermissionIdType();
    }

    @Test
    void test_ReturnedClass_ShouldReturnRoleIdClass() {
        assertEquals(PermissionId.class, underTest.returnedClass());
    }

    @Test
    void test_Assemble_ShouldReturnSameObject() {
        assertInstanceOf(PermissionId.class, underTest.assemble(new PermissionId(), null));
    }

    @Test
    void test_NewInstance_ShouldCreateNewInstanceBasedOnStringValue() {
        assertNotNull(underTest.newInstance(UUID.randomUUID().toString()));
    }
}
