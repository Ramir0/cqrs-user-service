package dev.amir.usercommand.domain.validator;

import dev.amir.usercommand.util.RandomObject;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {
    private static final String VALID_PASSWORD_VALUE = "testTEST1234$#@!";
    private PasswordValidator underTest;

    @BeforeEach
    void setUp() {
        underTest = new PasswordValidator();
    }

    @Test
    void test_Validate_ValidText_ReturnsNoError() {
        Set<AttributeErrorType> errors = underTest.validate(VALID_PASSWORD_VALUE);

        assertTrue(errors.isEmpty());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "   ", "\n", "\r\n"})
    void test_Validate_EmptyValue_ReturnsOneError(String value) {
        Set<AttributeErrorType> errors = underTest.validate(value);

        assertEquals(1, errors.size());
        assertTrue(errors.contains(AttributeErrorType.EMPTY_VALUE));
    }

    @Test
    void test_Validate_RandomText_ReturnsNoEmptyValueError() {
        String value = RandomObject.nextObject(String.class);

        Set<AttributeErrorType> errors = underTest.validate(value);

        assertFalse(errors.contains(AttributeErrorType.EMPTY_VALUE));
    }

    @Test
    void test_Validate_MinValue_ReturnsMinValueError() {
        String value = "A1$";

        Set<AttributeErrorType> errors = underTest.validate(value);

        assertTrue(errors.contains(AttributeErrorType.MIN_LIMIT_NOT_REACHED));
    }

    @Test
    void test_Validate_MaxValue_ReturnsMaxValueError() {
        String value = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijqlmnopqrstuvwxyz!@#$%^&*()";

        Set<AttributeErrorType> errors = underTest.validate(value);

        assertTrue(errors.contains(AttributeErrorType.MAX_LIMIT_EXCEEDED));
    }

    @Test
    void test_Validate_InvalidValue_ReturnsInvalidValueError() {
        String value = "1234Test  $#Test%";

        Set<AttributeErrorType> errors = underTest.validate(value);

        assertTrue(errors.contains(AttributeErrorType.INVALID_VALUE));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "test",
            "test_TEST_test",
            "Test123456789",
            "123#@!456^%$789(*&",
    })
    void test_Validate_InvalidFormat_ReturnsInvalidFormatError(String value) {
        Set<AttributeErrorType> errors = underTest.validate(value);

        assertTrue(errors.contains(AttributeErrorType.INVALID_FORMAT));
    }
}
