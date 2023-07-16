package dev.amir.userquery.framework.input.rest.response;

import dev.amir.userquery.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class GetAllUsersResponse {
    private Collection<User> users;
}
