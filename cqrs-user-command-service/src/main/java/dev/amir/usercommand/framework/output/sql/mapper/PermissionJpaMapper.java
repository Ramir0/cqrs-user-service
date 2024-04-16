package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.Permission;
import dev.amir.usercommand.framework.output.sql.entity.PermissionJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionJpaMapper {
    Permission convert(PermissionJpa permissionJpa);
}
