package dev.amir.cqrsusercommandservice.framework.input.rest.command;

public record UpdateUserCommand(String name, String lastname, String email, Boolean isActive) {
}
