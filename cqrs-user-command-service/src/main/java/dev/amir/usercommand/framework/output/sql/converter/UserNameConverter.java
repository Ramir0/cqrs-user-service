package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.FirstName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UserNameConverter implements AttributeConverter<FirstName, String> {

    @Override
    public String convertToDatabaseColumn(FirstName fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public FirstName convertToEntityAttribute(String columnValue) {
        return columnValue != null ? new FirstName(columnValue) : null;
    }
}
