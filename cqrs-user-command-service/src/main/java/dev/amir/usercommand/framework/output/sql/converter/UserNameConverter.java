package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UserNameConverter implements AttributeConverter<UserName, String> {

    @Override
    public String convertToDatabaseColumn(UserName fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public UserName convertToEntityAttribute(String columnValue) {
        return columnValue != null ? new UserName(columnValue) : null;
    }
}
