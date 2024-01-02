package dev.amir.usercommand.domain.valueobject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record UserLastName(
        @NotBlank
        @Size(min = 3, max = 15)
        @Pattern(regexp = "^[a-zA-Z\\s]+$")
        String value
) {

    @Override
    public String toString() {
        return value;
    }
}
