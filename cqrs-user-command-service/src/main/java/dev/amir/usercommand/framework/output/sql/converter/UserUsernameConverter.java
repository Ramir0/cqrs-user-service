package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.Username;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UserUsernameConverter implements AttributeConverter<Username, String> {
    @Override
    public String convertToDatabaseColumn(Username fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public Username convertToEntityAttribute(String columnValue) {
        return columnValue != null ? new Username(columnValue) : null;
    }
}
