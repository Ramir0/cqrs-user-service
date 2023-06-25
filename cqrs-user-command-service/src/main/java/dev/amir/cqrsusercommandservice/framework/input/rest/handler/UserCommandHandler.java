package dev.amir.cqrsusercommandservice.framework.input.rest.handler;

import dev.amir.cqrsusercommandservice.framework.input.rest.command.CreateUserCommand;

public interface UserCommandHandler {
    String handle(CreateUserCommand createUserCommand);
}
