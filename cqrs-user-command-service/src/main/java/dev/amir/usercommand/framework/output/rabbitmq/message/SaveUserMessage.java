package dev.amir.usercommand.framework.output.rabbitmq.message;

public record SaveUserMessage(String id, String name, String lastname, String email, Boolean active) {
}
