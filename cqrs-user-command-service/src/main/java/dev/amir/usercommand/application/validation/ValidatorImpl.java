package dev.amir.usercommand.application.validation;

import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidatorImpl implements Validator {
    private final javax.validation.Validator validator;

    @Override
    public <T> void validate(T object) {
        var errors = validator.validate(object);
        if (!errors.isEmpty()) {
            // TODO - Use custom exception
            throw new ConstraintViolationException("Object validation failed", errors);
        }
    }
}
