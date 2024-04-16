package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.Role;
import dev.amir.usercommand.framework.output.sql.entity.RoleJpa;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {PermissionJpaMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface RoleJpaMapper {
    Role convert(RoleJpa roleJpa);
}
