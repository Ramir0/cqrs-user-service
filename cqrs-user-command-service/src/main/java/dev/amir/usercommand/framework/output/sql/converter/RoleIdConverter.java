package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import jakarta.persistence.AttributeConverter;
import java.util.UUID;

public class RoleIdConverter implements AttributeConverter<RoleId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(RoleId fieldValue) {
        return fieldValue != null ? fieldValue.getValue() : null;
    }

    @Override
    public RoleId convertToEntityAttribute(UUID columnValue) {
        return columnValue != null ? new RoleId(columnValue) : null;
    }
}