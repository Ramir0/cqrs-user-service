package dev.amir.cqrsusercommandservice.framework.input.rest.mapper;

import dev.amir.cqrsusercommandservice.domain.entity.User;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.CreateUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.UpdateUserCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserCommandMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", source = "isActive")
    User convert(CreateUserCommand command);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", source = "isActive")
    User convert(UpdateUserCommand command);
}
