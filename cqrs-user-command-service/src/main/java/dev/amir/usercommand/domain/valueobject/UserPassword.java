package dev.amir.usercommand.domain.valueobject;

public record UserPassword(
        String value
) {
    @Override
    public String toString() {
        return value;
    }
}
