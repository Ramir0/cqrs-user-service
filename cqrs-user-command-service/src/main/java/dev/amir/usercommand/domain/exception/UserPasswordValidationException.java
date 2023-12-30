package dev.amir.usercommand.domain.exception;

import dev.amir.usercommand.domain.validator.AttributeErrorType;
import java.util.Set;
import lombok.Getter;

@Getter
public class UserPasswordValidationException extends UserValidationException {
    private final Set<AttributeErrorType> errors;

    public UserPasswordValidationException(Set<AttributeErrorType> errors) {
        super("The password does not meet the requirements", null);
        this.errors = errors;
    }
}
