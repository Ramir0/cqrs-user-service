package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserPassword;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class UserPasswordConverter implements AttributeConverter<UserPassword, String> {
    @Override
    public String convertToDatabaseColumn(UserPassword fieldValue) {
        return fieldValue.value();
    }

    @Override
    public UserPassword convertToEntityAttribute(String columnValue) {
        return new UserPassword(columnValue);
    }
}
