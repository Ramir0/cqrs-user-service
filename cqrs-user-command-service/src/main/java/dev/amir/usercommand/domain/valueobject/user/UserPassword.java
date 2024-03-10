package dev.amir.usercommand.domain.valueobject.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPassword(
        @NotBlank
        @Size(max = 255)
        String value
) {
    @Override
    public String toString() {
        return value;
    }
}
