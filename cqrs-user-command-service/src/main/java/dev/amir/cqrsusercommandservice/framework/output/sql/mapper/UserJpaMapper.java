package dev.amir.cqrsusercommandservice.framework.output.sql.mapper;

import dev.amir.cqrsusercommandservice.domain.entity.User;
import dev.amir.cqrsusercommandservice.framework.output.sql.entity.UserJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserJpaMapper {
    UserJpa convert(User user);
    User convert(UserJpa userJpa);
}
