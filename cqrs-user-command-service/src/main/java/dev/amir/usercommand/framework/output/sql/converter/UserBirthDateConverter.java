package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.BirthDate;
import jakarta.persistence.AttributeConverter;
import java.time.LocalDate;

public class UserBirthDateConverter implements AttributeConverter<BirthDate, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(BirthDate fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public BirthDate convertToEntityAttribute(LocalDate columnValue) {
        return columnValue != null ? new BirthDate(columnValue) : null;
    }
}

