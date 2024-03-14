package dev.amir.usercommand.domain.valueobject.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Email(
        @NotBlank
        @Size(min = 10, max = 150)
        @jakarta.validation.constraints.Email(regexp = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:"
                + "[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
        String value
) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Email(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
