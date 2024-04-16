package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.permission.Name;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PermissionNameConverter implements AttributeConverter<Name, String> {
    @Override
    public String convertToDatabaseColumn(Name fieldValue) {
        return fieldValue != null ? fieldValue.value() : null;
    }

    @Override
    public Name convertToEntityAttribute(String columnValue) {
        return columnValue != null ? new Name(columnValue) : null;
    }
}
