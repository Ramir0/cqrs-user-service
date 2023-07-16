package dev.amir.usercommand.framework.input.rest.command;

public record CreateUserCommand(String name, String lastname, String email, Boolean isActive) {
}
