package dev.amir.usercommand.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserEmail(
        @NotBlank
        @Size(min = 10, max = 150)
        @Email(regexp = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:"
                + "\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
        String value
) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserEmail(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
