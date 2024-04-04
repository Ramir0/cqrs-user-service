package dev.amir.usercommand.framework.output.sql.type;

import dev.amir.usercommand.domain.valueobject.user.UserId;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserIdTypeTest {
    private UserIdType underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserIdType();
    }

    @Test
    void test_ReturnedClass_ShouldReturnRoleIdClass() {
        assertEquals(UserId.class, underTest.returnedClass());
    }

    @Test
    void test_Assemble_ShouldReturnSameObject() {
        assertInstanceOf(UserId.class, underTest.assemble(new UserId(), null));
    }

    @Test
    void test_NewInstance_ShouldCreateNewInstanceBasedOnStringValue() {
        assertNotNull(underTest.newInstance(UUID.randomUUID().toString()));
    }
}
