package dev.amir.userquery.framework.output.sql.mapper;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.sql.entity.UserJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {

    User convert(UserJpa userJpa);

    UserJpa convert(User user);
}
