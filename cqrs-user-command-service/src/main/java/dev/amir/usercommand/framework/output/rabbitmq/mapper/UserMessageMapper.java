package dev.amir.usercommand.framework.output.rabbitmq.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.output.rabbitmq.message.SaveUserMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMessageMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "userIdToString")
    SaveUserMessage convert(User user);

    @Named("userIdToString")
    default String userIdToString(UserId id) {
        return id.toString();
    }
}
