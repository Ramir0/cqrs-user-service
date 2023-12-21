package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.RoleId;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserJpaMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "userIdToUuid")
    @Mapping(source = "roleId", target = "roleId", qualifiedByName = "roleIdToUuid")
    @Mapping(source = "password", target = "password", qualifiedByName = "userPasswordToString")
    UserJpa convert(User user);

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToUserId")
    @Mapping(source = "roleId", target = "roleId", qualifiedByName = "uuidToRoleId")
    @Mapping(source = "password", target = "password", qualifiedByName = "stringToUserPassword")
    User convert(UserJpa userJpa);

    @Named("userIdToUuid")
    default UUID userIdToUuid(UserId id) {
        return id != null ? id.getValue() : null;
    }

    @Named("uuidToUserId")
    default UserId uuidToUserId(UUID id) {
        return id != null ? new UserId(id) : null;
    }

    @Named("roleIdToUuid")
    default UUID roleIdToUuid(RoleId roleId) {
        return roleId != null ? roleId.getValue() : null;
    }

    @Named("uuidToRoleId")
    default RoleId uuidToRoleId(UUID roleId) {
        return roleId != null ? new RoleId(roleId) : null;
    }

    @Named("userPasswordToString")
    default String userPasswordToString(UserPassword password) {
        return password != null ? password.toString() : null;
    }

    @Named("stringToUserPassword")
    default UserPassword stringToUserPassword(String password) {
        return password != null ? new UserPassword(password) : null;
    }
}
