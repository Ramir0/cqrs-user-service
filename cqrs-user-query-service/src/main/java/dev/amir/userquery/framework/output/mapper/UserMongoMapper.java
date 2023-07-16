package dev.amir.userquery.framework.output.mapper;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.entity.UserMongo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMongoMapper {
    User convert(UserMongo userMongo);
    UserMongo convert(User user);
}
