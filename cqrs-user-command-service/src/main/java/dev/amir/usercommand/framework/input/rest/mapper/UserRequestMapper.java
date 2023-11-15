package dev.amir.usercommand.framework.input.rest.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User convert(CreateUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User convert(UpdateUserRequest request);
}
