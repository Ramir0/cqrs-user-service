package dev.amir.usercommand.domain.valueobject.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.amir.usercommand.domain.validator.DateRange;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record BirthDate(
        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @DateRange(yearsFromToDay = -18)
        LocalDate value
) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public BirthDate(LocalDate value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
