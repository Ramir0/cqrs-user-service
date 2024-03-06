package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserBirthDate;
import jakarta.persistence.AttributeConverter;
import java.time.LocalDate;

public class UserBirthDateConverter implements AttributeConverter<UserBirthDate, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(UserBirthDate fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public UserBirthDate convertToEntityAttribute(LocalDate columnValue) {
        return columnValue != null ? new UserBirthDate(columnValue) : null;
    }
}

