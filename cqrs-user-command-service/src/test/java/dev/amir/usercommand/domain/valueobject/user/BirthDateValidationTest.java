package dev.amir.usercommand.domain.valueobject.user;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BirthDateValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1900-01-01", "2005-08-20", "1990-06-15", "1999-12-31"})
    void test_ValidValue_ReturnsNoConstraintViolation(String value) {
        BirthDate birthDate = new BirthDate(LocalDate.parse(value));

        Set<ConstraintViolation<BirthDate>> violations = validator.validate(birthDate);

        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1800-01-01", "2020-05-09", "2016-02-08", "2100-01-01", "1899-12-31"})
    void test_InvalidValue_ReturnsConstraintViolation(String value) {
        BirthDate birthDate = new BirthDate(LocalDate.parse(value));

        Set<ConstraintViolation<BirthDate>> violations = validator.validate(birthDate);

        assertFalse(violations.isEmpty());
    }

    @Test
    void test_NullValue_ReturnsNoConstraintViolation() {
        BirthDate birthDate = new BirthDate(null);

        Set<ConstraintViolation<BirthDate>> violations = validator.validate(birthDate);

        assertFalse(violations.isEmpty());
    }
}
