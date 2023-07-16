package dev.amir.usercommand.framework.output.rabbitmq.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.rabbitmq.message.CreateUserMessage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMessageMapper {
    CreateUserMessage convert(User user);
}
