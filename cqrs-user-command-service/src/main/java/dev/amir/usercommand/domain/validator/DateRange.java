package dev.amir.usercommand.domain.validator;

import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
public @interface DateRange {
    String message() default "The date does not meet the range restrictions.";

    String min() default "1900-01-01";

    int yearsFromToDay();

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
