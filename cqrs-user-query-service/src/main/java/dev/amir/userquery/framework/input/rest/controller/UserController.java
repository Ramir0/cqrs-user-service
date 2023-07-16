package dev.amir.userquery.framework.input.rest.controller;

import dev.amir.userquery.framework.input.rest.handler.UserQueryHandler;
import dev.amir.userquery.framework.input.rest.query.GetUserByIdQuery;
import dev.amir.userquery.framework.input.rest.response.GetAllUsersResponse;
import dev.amir.userquery.framework.input.rest.response.GetUserByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserQueryHandler userQueryHandler;

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        return ResponseEntity.ok(userQueryHandler.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserByIdResponse> getUserById(GetUserByIdQuery query) {
        return ResponseEntity.ok(userQueryHandler.getUserById(query));
    }
}
