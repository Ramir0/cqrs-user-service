package dev.amir.userquery.framework.input.rest.response;

import dev.amir.userquery.domain.entity.User;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllUsersResponse {
    private Collection<User> users;
}
