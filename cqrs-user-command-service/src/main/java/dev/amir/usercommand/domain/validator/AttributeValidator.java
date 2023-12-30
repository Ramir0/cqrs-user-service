package dev.amir.usercommand.domain.validator;

import java.util.Set;

public interface AttributeValidator<T> {
    Set<AttributeErrorType> validate(T value);
}
