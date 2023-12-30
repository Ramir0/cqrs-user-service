package dev.amir.usercommand.domain.valueobject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
