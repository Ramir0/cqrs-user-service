package dev.amir.usercommand.framework.input.rest.controller;

import dev.amir.usercommand.framework.input.rest.handler.UserHandler;
import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.DeleteUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import dev.amir.usercommand.framework.input.rest.response.CreateUserResponse;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserHandler userHandler;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        log.info("Received request to create a user");
        CreateUserResponse response = userHandler.handle(request);

        log.info("User with ID {} has been successfully created", response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@NotNull @PathVariable UUID id, @RequestBody UpdateUserRequest request) {
        log.info("Received request to update");

        try {
            userHandler.handle(request, id);
            log.info("User with ID {} has been successfully update", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error updating user with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@NotNull @PathVariable UUID id) {
        log.info("Received request to delete user");
        boolean isUserDeleted = userHandler.handle(new DeleteUserRequest(), id);

        if (isUserDeleted) {
            log.info("User with ID {} has been successfully deleted", id);
        } else {
            log.warn("An unexpected error occurred while deleting the user");
        }

        return ResponseEntity.ok(isUserDeleted);
    }
}
