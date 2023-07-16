package dev.amir.userquery.framework.input.rabbitmq.message;

public record CreateUserMessage(String id, String name, String lastname, String email, Boolean active) {
}
