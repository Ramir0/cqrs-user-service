package dev.amir.usercommand.framework.output.sql.type;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoleIdTypeTest {
    private RoleIdType underTest;

    @BeforeEach
    void setUp() {
        underTest = new RoleIdType();
    }

    @Test
    void test_ReturnedClass_ShouldReturnRoleIdClass() {
        assertEquals(RoleId.class, underTest.returnedClass());
    }

    @Test
    void test_Assemble_ShouldReturnSameObject() {
        assertInstanceOf(RoleId.class, underTest.assemble(new RoleId(), null));
    }

    @Test
    void test_NewInstance_ShouldCreateNewInstanceBasedOnStringValue() {
        assertNotNull(underTest.newInstance(UUID.randomUUID().toString()));
    }
}
