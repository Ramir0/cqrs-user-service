package dev.amir.userquery.framework.input.rest.handler;

import dev.amir.userquery.framework.input.rest.query.GetUserByIdQuery;
import dev.amir.userquery.framework.input.rest.response.GetAllUsersResponse;
import dev.amir.userquery.framework.input.rest.response.GetUserByIdResponse;

public interface UserQueryHandler {

    GetAllUsersResponse getAllUsers();

    GetUserByIdResponse getUserById(GetUserByIdQuery query);
}
