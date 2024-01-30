package dev.amir.usercommand.domain.valueobject;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserBirthDateValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @ParameterizedTest
    @ValueSource(strings = {"1900-01-01", "2000-05-10", "1998-02-08"})
    void test_ValidValue_ReturnsNoConstraintViolation(String value) {
        UserBirthDate birthDate = new UserBirthDate(LocalDate.parse(value));

        Set<ConstraintViolation<UserBirthDate>> violations = validator.validate(birthDate);

        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1800-01-01", "2020-05-09", "2016-02-08"})
    void test_InvalidValue_ReturnsConstraintViolation(String value) {
        UserBirthDate birthDate = new UserBirthDate(LocalDate.parse(value));

        Set<ConstraintViolation<UserBirthDate>> violations = validator.validate(birthDate);

        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @NullSource
    void test_NullValue_ReturnsNoConstraintViolation(LocalDate value) {
        UserBirthDate birthDate = new UserBirthDate(value);

        Set<ConstraintViolation<UserBirthDate>> violations = validator.validate(birthDate);

        assertTrue(violations.isEmpty());
    }
}
