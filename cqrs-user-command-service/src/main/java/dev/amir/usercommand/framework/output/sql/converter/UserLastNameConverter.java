package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserLastName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UserLastNameConverter implements AttributeConverter<UserLastName, String> {
    @Override
    public String convertToDatabaseColumn(UserLastName fieldValue) {
        return fieldValue.value();
    }

    @Override
    public UserLastName convertToEntityAttribute(String columnValue) {
        return new UserLastName(columnValue);
    }
}
