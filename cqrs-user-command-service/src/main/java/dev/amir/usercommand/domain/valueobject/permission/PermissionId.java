package dev.amir.usercommand.domain.valueobject.permission;

import dev.amir.usercommand.domain.exception.UserValidationException;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PermissionId implements Serializable {
    private final UUID value;

    public PermissionId() {
        value = UUID.randomUUID();
    }

    public PermissionId(String valueAsString) {
        try {
            this.value = UUID.fromString(valueAsString);
        } catch (Exception exception) {
            throw new UserValidationException(
                    "Invalid value, PermissionId cannot be initialized: " + valueAsString,
                    exception
            );
        }
    }

    public PermissionId(UUID value) {
        if (Objects.isNull(value)) {
            throw new UserValidationException("Invalid value, PermissionId cannot be null");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
