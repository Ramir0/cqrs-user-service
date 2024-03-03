package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserEmail;
import jakarta.persistence.AttributeConverter;

public class UserEmailConverter implements AttributeConverter<UserEmail, String> {

    @Override
    public String convertToDatabaseColumn(UserEmail fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public UserEmail convertToEntityAttribute(String columnValue) {
        return columnValue != null ? new UserEmail(columnValue) : null;
    }
}
