package dev.amir.usercommand.framework.input.rest.request;

public record UpdateUserRequest(String name, String lastname, String email, Boolean isActive) {
}
