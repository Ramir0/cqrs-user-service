package dev.amir.userquery.framework.input.rest.response;

import dev.amir.userquery.domain.entity.User;
import lombok.Data;

@Data
public class GetUserByIdResponse {
    User user;
}
