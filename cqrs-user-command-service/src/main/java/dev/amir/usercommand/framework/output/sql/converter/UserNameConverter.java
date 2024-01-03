package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserName;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

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
