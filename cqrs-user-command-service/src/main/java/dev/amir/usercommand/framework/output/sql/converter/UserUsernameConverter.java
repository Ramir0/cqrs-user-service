package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserUsername;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UserUsernameConverter implements AttributeConverter<UserUsername, String> {
    @Override
    public String convertToDatabaseColumn(UserUsername fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public UserUsername convertToEntityAttribute(String columnValue) {
        return columnValue != null ? new UserUsername(columnValue) : null;
    }
}
