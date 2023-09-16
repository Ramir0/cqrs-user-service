package dev.amir.userquery.framework.input.rest.controller;

import dev.amir.userquery.framework.input.rest.handler.UserQueryHandler;
import dev.amir.userquery.framework.input.rest.query.GetAllUsersQuery;
import dev.amir.userquery.framework.input.rest.query.GetUserByIdQuery;
import dev.amir.userquery.framework.input.rest.response.GetAllUsersResponse;
import dev.amir.userquery.framework.input.rest.response.GetUserByIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserQueryHandler userQueryHandler;

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        log.info("Received request to get all users.");
        return ResponseEntity.ok(userQueryHandler.handle(new GetAllUsersQuery()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserByIdResponse> getUserById(GetUserByIdQuery query) {
        log.debug("Received request to get user by ID.");
        return ResponseEntity.ok(userQueryHandler.handle(query));
    }
}
