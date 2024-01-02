package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserLastName;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

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
