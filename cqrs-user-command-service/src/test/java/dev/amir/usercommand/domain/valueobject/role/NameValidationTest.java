package dev.amir.usercommand.domain.valueobject.role;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"TEST", "ROLE TEST", "Role Test", "role"})
    void test_ValidValue_ReturnsNoConstraintViolation(String value) {
        Name underTest = new Name(value);

        Set<ConstraintViolation<Name>> violations = validator.validate(underTest);

        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test123", "Role_Test", "role-test", "rt", "1"})
    @NullSource
    void test_InvalidValue_ReturnsConstraintViolation(String value) {
        Name underTest = new Name(value);

        Set<ConstraintViolation<Name>> violations = validator.validate(underTest);

        assertFalse(violations.isEmpty());
    }
}
