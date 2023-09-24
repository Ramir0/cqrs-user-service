package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserJpaMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUUID")
    UserJpa convert(User user);

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    User convert(UserJpa userJpa);

    @Named("stringToUUID")
    default UUID stringToUUID(String id) {
        return id != null ? UUID.fromString(id) : null;
    }

    @Named("uuidToString")
    default String uuidToString(UUID id) {
        return id != null ? id.toString() : null;
    }
}
