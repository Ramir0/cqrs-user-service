package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserUsername;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class UserUsernameConverter implements AttributeConverter<UserUsername, String> {
    @Override
    public String convertToDatabaseColumn(UserUsername fieldValue) {
        return fieldValue.value();
    }

    @Override
    public UserUsername convertToEntityAttribute(String columnValue) {
        return new UserUsername(columnValue);
    }
}
