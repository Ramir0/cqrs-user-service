package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.LastName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UserLastNameConverter implements AttributeConverter<LastName, String> {
    @Override
    public String convertToDatabaseColumn(LastName fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public LastName convertToEntityAttribute(String columnValue) {
        return columnValue != null ? new LastName(columnValue) : null;
    }
}
