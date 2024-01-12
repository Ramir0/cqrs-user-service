package dev.amir.usercommand.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUsername(
        @NotBlank
        @Size(min = 6, max = 15)
        @Pattern(regexp = "^[A-Za-z0-9]+(?:[._-][A-Za-z0-9]+)*$")
        String value
) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserUsername(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
