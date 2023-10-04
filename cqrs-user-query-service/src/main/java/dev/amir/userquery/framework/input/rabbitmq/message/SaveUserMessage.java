package dev.amir.userquery.framework.input.rabbitmq.message;

public record SaveUserMessage(String id, String name, String lastname, String email, Boolean active) {
}
