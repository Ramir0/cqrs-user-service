package dev.amir.usercommand.framework.output.rabbitmq.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.rabbitmq.message.SaveUserMessage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMessageMapper {
    SaveUserMessage convert(User user);
}
