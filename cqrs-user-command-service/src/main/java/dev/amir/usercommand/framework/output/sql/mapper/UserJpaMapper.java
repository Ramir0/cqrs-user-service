package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserJpaMapper {
    UserJpa convert(User user);

    User convert(UserJpa userJpa);
}
