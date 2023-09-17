package dev.amir.usercommand.framework.input.rest.controller;

import dev.amir.usercommand.framework.input.rest.command.CreateUserCommand;
import dev.amir.usercommand.framework.input.rest.command.DeleteUserCommand;
import dev.amir.usercommand.framework.input.rest.command.UpdateUserCommand;
import dev.amir.usercommand.framework.input.rest.handler.UserCommandHandler;
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
    private final UserCommandHandler commandHandler;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserCommand command) {
        log.info("Received request to create a user.");
        String newUserId = commandHandler.handle(command);

        log.info("User with ID {} has been successfully created", newUserId);
        return ResponseEntity.ok(newUserId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UpdateUserCommand command) {
        log.info("Received request to update");

        try {
            commandHandler.handle(command, id);
            log.info("User with ID {} has been successfully update", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error updating user with ID {}: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) {
        log.info("Received request to delete user");
        boolean isUserDeleted = commandHandler.handle(new DeleteUserCommand(), id);

        if (isUserDeleted) {
            log.info("User with ID {} has been successfully deleted", id);
        } else {
            log.warn("An unexpected error occurred while deleting the user");
        }

        return ResponseEntity.ok(isUserDeleted);
    }
}
