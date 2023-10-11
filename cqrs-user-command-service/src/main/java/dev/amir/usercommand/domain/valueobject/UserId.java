package dev.amir.usercommand.domain.valueobject;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@EqualsAndHashCode
public class UserId {
    @NotNull
    private final UUID value;

    public UserId() {
        value = UUID.randomUUID();
    }

    public UserId(String valueAsString) {
        this.value = UUID.fromString(valueAsString);
    }

    public UserId(UUID value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
