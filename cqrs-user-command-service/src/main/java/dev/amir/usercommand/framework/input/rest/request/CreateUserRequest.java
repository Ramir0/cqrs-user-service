package dev.amir.usercommand.framework.input.rest.request;

public record CreateUserRequest(String name, String lastname, String email, Boolean isActive) {
}
