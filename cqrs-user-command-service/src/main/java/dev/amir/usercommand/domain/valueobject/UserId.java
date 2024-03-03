package dev.amir.usercommand.domain.valueobject;

import dev.amir.usercommand.domain.exception.UserValidationException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Embeddable
public class UserId {
    @Column(name = "id")
    private final UUID value;

    public UserId() {
        value = UUID.randomUUID();
    }

    public UserId(String valueAsString) {
        try {
            this.value = UUID.fromString(valueAsString);
        } catch (Exception exception) {
            throw new UserValidationException(
                    "Invalid value, UserId cannot be initialized: " + valueAsString,
                    exception
            );
        }
    }

    public UserId(UUID value) {
        if (Objects.isNull(value)) {
            throw new UserValidationException("Invalid value, UserId cannot be null");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
