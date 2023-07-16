package dev.amir.usercommand.framework.output.rabbitmq.message;

public record CreateUserMessage(String id, String name, String lastname, String email, Boolean active) {
}
