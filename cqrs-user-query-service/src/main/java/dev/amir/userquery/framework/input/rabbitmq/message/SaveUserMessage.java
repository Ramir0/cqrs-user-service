package dev.amir.userquery.framework.input.rabbitmq.message;

import dev.amir.userquery.domain.valueobject.UserStatus;

public record SaveUserMessage(String id, String name, String lastname, String email, UserStatus status) {
}
