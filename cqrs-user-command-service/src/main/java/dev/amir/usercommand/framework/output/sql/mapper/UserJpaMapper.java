package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.RoleId;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserJpaMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "roleId", target = "roleId", qualifiedByName = "roleIdToUuid")
    UserJpa convert(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "roleId", target = "roleId", qualifiedByName = "uuidToRoleId")
    User convert(UserJpa userJpa);

    @Named("roleIdToUuid")
    default UUID roleIdToUuid(RoleId roleId) {
        return roleId != null ? roleId.getValue() : null;
    }

    @Named("uuidToRoleId")
    default RoleId uuidToRoleId(UUID roleId) {
        return roleId != null ? new RoleId(roleId) : null;
    }
}
