package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UserNameConverter implements AttributeConverter<UserName, String> {

    @Override
    public String convertToDatabaseColumn(UserName fieldValue) {
        return fieldValue.value();
    }

    @Override
    public UserName convertToEntityAttribute(String columnValue) {
        return new UserName(columnValue);
    }
}
