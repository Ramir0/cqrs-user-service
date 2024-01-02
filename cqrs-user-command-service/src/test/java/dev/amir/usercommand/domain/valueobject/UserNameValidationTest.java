package dev.amir.usercommand.domain.valueobject;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserNameValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void test_ValidValue_ReturnsNoConstraintViolation() {
        UserName username = new UserName("User Name");

        Set<ConstraintViolation<UserName>> violations = validator.validate(username);

        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "   ", "\n", "\r\n"})
    void test_NoValue_ReturnsOneConstraintViolation(String value) {
        UserName name = new UserName(value);

        Set<ConstraintViolation<UserName>> violations = validator.validate(name);

        assertFalse(violations.isEmpty());
        boolean existNoValueViolation = violations
                .stream()
                .anyMatch(violation -> violation.getMessage().equals("must not be blank"));
        assertTrue(existNoValueViolation);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Aa", "test  TEST  test Test"})
    void test_MinMaxValue_ReturnsOneConstraintViolation(String value) {
        UserName name = new UserName(value);

        Set<ConstraintViolation<UserName>> violations = validator.validate(name);

        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertInstanceOf(ConstraintViolationImpl.class, violation);
            assertTrue(violation.getMessage().startsWith("size must be between "));
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"Abc$123", "Abc__123", "123$%^321", "!@#$%^&*"})
    void test_InvalidValue_ReturnsOneConstraintViolation(String value) {
        UserName name = new UserName(value);

        Set<ConstraintViolation<UserName>> violations = validator.validate(name);

        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertInstanceOf(ConstraintViolationImpl.class, violation);
            assertTrue(violation.getMessage().startsWith("must match "));
        });
    }
}
