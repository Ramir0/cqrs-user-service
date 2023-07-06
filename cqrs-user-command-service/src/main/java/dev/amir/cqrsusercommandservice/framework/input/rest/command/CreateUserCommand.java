package dev.amir.cqrsusercommandservice.framework.input.rest.command;

public record CreateUserCommand(String name, String lastname, String email, Boolean isActive) {
}
