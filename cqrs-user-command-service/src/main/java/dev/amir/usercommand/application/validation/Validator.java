package dev.amir.usercommand.application.validation;

public interface Validator {
    <T> void validate(T object);
}
