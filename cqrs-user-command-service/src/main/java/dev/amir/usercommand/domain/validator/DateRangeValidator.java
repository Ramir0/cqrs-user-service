package dev.amir.usercommand.domain.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;


public class DateRangeValidator implements ConstraintValidator<DateRange, LocalDate> {

    private LocalDate minDate;
    private int yearsFromToDay;

    @Override
    public void initialize(DateRange constraintAnnotation) {
        minDate = LocalDate.parse(constraintAnnotation.min());
        yearsFromToDay = constraintAnnotation.yearsFromToDay();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate maxDateLimit = currentDate.plusYears(yearsFromToDay);

        return !value.isBefore(minDate) && !value.isAfter(maxDateLimit);
    }
}
