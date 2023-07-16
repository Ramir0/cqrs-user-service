package dev.amir.userquery.framework.input.rabbitmq.mapper;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.input.rabbitmq.message.CreateUserMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMessageMapper {

    @Mapping(target = "active", source = "active")
    User convert(CreateUserMessage message);
}