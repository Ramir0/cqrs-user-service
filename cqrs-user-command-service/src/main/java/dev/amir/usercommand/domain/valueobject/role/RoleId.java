package dev.amir.usercommand.domain.valueobject.role;

import dev.amir.usercommand.domain.exception.UserValidationException;
import jakarta.persistence.Column;
import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class RoleId {
    @Column(name = "role_id")
    private final UUID value;

    public RoleId() {
        value = UUID.randomUUID();
    }

    public RoleId(String valueAsString) {
        try {
            this.value = UUID.fromString(valueAsString);
        } catch (Exception exception) {
            throw new UserValidationException(
                    "Invalid value, RoleId cannot be initialized: " + valueAsString,
                    exception
            );
        }
    }

    public RoleId(UUID value) {
        if (Objects.isNull(value)) {
            throw new UserValidationException("Invalid value, RoleId cannot be null");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
