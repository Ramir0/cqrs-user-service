package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class UserIdConverter implements AttributeConverter<UserId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(UserId fieldValue) {
        return fieldValue.getValue();
    }

    @Override
    public UserId convertToEntityAttribute(UUID columnValue) {
        return new UserId(columnValue);
    }
}
