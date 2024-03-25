package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.Email;
import jakarta.persistence.AttributeConverter;

public class UserEmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public Email convertToEntityAttribute(String columnValue) {
        return columnValue != null ? new Email(columnValue) : null;
    }
}
