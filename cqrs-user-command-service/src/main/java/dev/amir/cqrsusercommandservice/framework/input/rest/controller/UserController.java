package dev.amir.cqrsusercommandservice.framework.input.rest.controller;

import dev.amir.cqrsusercommandservice.framework.input.rest.command.CreateUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.handler.UserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserCommandHandler commandHandler;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserCommand createUserCommand) {
        String newUserId = commandHandler.handle(createUserCommand);

        return ResponseEntity.ok(newUserId);
    }
}
