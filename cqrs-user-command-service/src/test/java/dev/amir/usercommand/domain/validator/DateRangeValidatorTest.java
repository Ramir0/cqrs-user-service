package dev.amir.usercommand.domain.validator;

import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class DateRangeValidatorTest {

    private DateRangeValidator underTest;

    @BeforeEach
    void setUp() {
        underTest = new DateRangeValidator();
    }

    @Test
    public void test_ValidDate() {
        underTest.initialize(createDateRangeAnnotation());

        LocalDate validDate = LocalDate.of(2006, 1, 9);
        assertTrue(underTest.isValid(validDate, mock(ConstraintValidatorContext.class)));
    }

    @Test
    public void test_InvalidDateBeforeMinDate() {
        underTest.initialize(createDateRangeAnnotation());

        LocalDate invalidDate = LocalDate.of(1800, 1, 1);
        assertFalse(underTest.isValid(invalidDate, mock(ConstraintValidatorContext.class)));
    }

    @Test
    public void test_InvalidDateAfterMaxDate() {
        underTest.initialize(createDateRangeAnnotation());

        LocalDate invalidDate = LocalDate.now().minusYears(-17);
        assertFalse(underTest.isValid(invalidDate, mock(ConstraintValidatorContext.class)));
    }

    @Test
    public void test_ValidNullDate() {
        underTest.initialize(createDateRangeAnnotation());

        assertTrue(underTest.isValid(null, mock(ConstraintValidatorContext.class)));
    }

    private DateRange createDateRangeAnnotation() {
        return new DateRange() {
            @Override
            public String message() {
                return "The date does not meet the range restrictions.";
            }

            @Override
            public String min() {
                return "1900-01-01";
            }

            @Override
            public int yearsFromToDay() {
                return -18;
            }

            @Override
            public Class<?>[] groups() {
                return new Class<?>[0];
            }

            @Override
            public Class<?>[] payload() {
                return new Class<?>[0];
            }

            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return DateRange.class;
            }

        };
    }
}
