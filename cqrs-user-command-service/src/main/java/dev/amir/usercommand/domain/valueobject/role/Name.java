package dev.amir.usercommand.domain.valueobject.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Name(@NotBlank
                   @Size(min = 3, max = 15)
                   @Pattern(regexp = "^[a-zA-Z\\s]+$")
                   String value
) {
    @Override
    public String toString() {
        return value;
    }
}
