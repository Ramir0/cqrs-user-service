package dev.amir.usercommand.framework.output.rabbitmq.message;

import dev.amir.usercommand.domain.valueobject.UserStatus;

public record SaveUserMessage(String id, String name, String lastname, String email, UserStatus status) {
}
