package dev.amir.usercommand.domain.valueobject.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.amir.usercommand.domain.validator.DateRange;
import jakarta.annotation.Nullable;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record UserBirthDate(
        @Nullable
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @DateRange(min = "1900-01-01", yearsFromToDay = -18)
        LocalDate value
) {

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserBirthDate(LocalDate value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
