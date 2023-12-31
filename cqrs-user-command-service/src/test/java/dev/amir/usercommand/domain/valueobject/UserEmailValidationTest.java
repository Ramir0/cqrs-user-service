package dev.amir.usercommand.domain.valueobject;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserEmailValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"usr@test.com", "test@local", "User_4551@test.com"})
    void test_ValidValue_ReturnsNoConstraintViolation(String value) {
        UserEmail email = new UserEmail(value);

        Set<ConstraintViolation<UserEmail>> violations = validator.validate(email);

        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "   ", "\n", "\r\n"})
    void test_NoValue_ReturnsOneConstraintViolation(String value) {
        UserEmail email = new UserEmail(value);

        Set<ConstraintViolation<UserEmail>> violations = validator.validate(email);

        assertFalse(violations.isEmpty());
        boolean existNoValueViolation = violations
                .stream()
                .anyMatch(violation -> violation.getMessage().equals("must not be blank"));
        assertTrue(existNoValueViolation);
    }

    @ParameterizedTest
    @ValueSource(strings = {"u@gmai.co"})
    void test_MinMaxValue_ReturnsOneConstraintViolation(String value) {
        UserEmail email = new UserEmail(value);

        Set<ConstraintViolation<UserEmail>> violations = validator.validate(email);

        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertInstanceOf(ConstraintViolationImpl.class, violation);
            assertTrue(violation.getMessage().startsWith("size must be between "));
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"R,abc@def.com", "Abc_@_ 123com", "123$%@^321com", "!@#$%^&*com"})
    void test_InvalidValue_ReturnsOneConstraintViolation(String value) {
        UserEmail email = new UserEmail(value);

        Set<ConstraintViolation<UserEmail>> violations = validator.validate(email);

        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertInstanceOf(ConstraintViolationImpl.class, violation);
            assertEquals("must be a well-formed email address", violation.getMessage());
        });
    }
}
