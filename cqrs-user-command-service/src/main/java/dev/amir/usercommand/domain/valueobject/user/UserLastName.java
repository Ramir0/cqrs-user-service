package dev.amir.usercommand.domain.valueobject.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserLastName(
        @NotBlank
        @Size(min = 3, max = 15)
        @Pattern(regexp = "^[a-zA-Z\\s]+$")
        String value
) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserLastName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
