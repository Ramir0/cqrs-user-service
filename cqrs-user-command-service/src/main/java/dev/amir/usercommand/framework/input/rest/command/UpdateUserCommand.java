package dev.amir.usercommand.framework.input.rest.command;

public record UpdateUserCommand(String name, String lastname, String email, Boolean isActive) {
}
