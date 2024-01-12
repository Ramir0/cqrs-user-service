package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserEmail;
import jakarta.persistence.AttributeConverter;

public class UserEmailConverter implements AttributeConverter<UserEmail, String> {

    @Override
    public String convertToDatabaseColumn(UserEmail fieldValue) {
        return fieldValue.value();
    }

    @Override
    public UserEmail convertToEntityAttribute(String columValue) {
        return new UserEmail(columValue);
    }
}
