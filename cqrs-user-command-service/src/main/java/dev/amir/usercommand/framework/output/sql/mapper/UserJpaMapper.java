package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserJpaMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "userIdToUuid")
    UserJpa convert(User user);

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToUserId")
    User convert(UserJpa userJpa);

    @Named("userIdToUuid")
    default UUID userIdToUuid(UserId id) {
        return id != null ? id.getValue() : null;
    }

    @Named("uuidToUserId")
    default UserId uuidToUserId(UUID id) {
        return id != null ? new UserId(id) : null;
    }
}
