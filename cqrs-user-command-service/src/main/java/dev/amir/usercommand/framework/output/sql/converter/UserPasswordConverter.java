package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.Password;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserPasswordConverter implements AttributeConverter<Password, String> {
    @Override
    public String convertToDatabaseColumn(Password fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public Password convertToEntityAttribute(String columnValue) {
        return columnValue != null ? new Password(columnValue) : null;
    }
}
