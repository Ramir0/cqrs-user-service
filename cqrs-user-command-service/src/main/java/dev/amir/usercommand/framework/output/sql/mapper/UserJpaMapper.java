package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserJpaMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "roleId", target = "roleId")
    UserJpa convert(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "roleId", target = "roleId")
    User convert(UserJpa userJpa);

}
