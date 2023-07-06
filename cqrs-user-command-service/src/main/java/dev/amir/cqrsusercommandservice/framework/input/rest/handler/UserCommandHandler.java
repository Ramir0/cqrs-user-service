package dev.amir.cqrsusercommandservice.framework.input.rest.handler;

import dev.amir.cqrsusercommandservice.framework.input.rest.command.CreateUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.DeleteUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.UpdateUserCommand;

public interface UserCommandHandler {
    String handle(CreateUserCommand command);
    void handle(UpdateUserCommand command, String userId);
    boolean handle(DeleteUserCommand command, String userId);
}
