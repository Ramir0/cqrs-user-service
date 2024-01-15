package dev.amir.usercommand.framework.input.rest.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserRequestMapper {
    User convert(CreateUserRequest request);

    User convert(UpdateUserRequest request);

    User convert(ChangePasswordRequest request);
}
