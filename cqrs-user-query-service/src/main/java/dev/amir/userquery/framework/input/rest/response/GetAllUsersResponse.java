package dev.amir.userquery.framework.input.rest.response;

import dev.amir.userquery.domain.entity.User;
import java.util.Collection;

public record GetAllUsersResponse(Collection<User> users) {
}
