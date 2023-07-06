package dev.amir.cqrsusercommandservice.framework.input.rest.controller;

import dev.amir.cqrsusercommandservice.framework.input.rest.command.CreateUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.DeleteUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.UpdateUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.handler.UserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserCommandHandler commandHandler;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserCommand command) {
        String newUserId = commandHandler.handle(command);

        return ResponseEntity.ok(newUserId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UpdateUserCommand command) {
        commandHandler.handle(command, id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) {
        boolean isUserDeleted = commandHandler.handle(new DeleteUserCommand(), id);

        return ResponseEntity.ok(isUserDeleted);
    }
}
