package dev.amir.usercommand.domain.valueobject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserPassword(
        @NotNull
        @Size(max = 255)
        String value
) {
    @Override
    public String toString() {
        return value;
    }
}
