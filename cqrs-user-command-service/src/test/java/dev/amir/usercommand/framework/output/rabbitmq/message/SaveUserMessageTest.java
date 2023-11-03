package dev.amir.usercommand.framework.output.rabbitmq.message;

import dev.amir.usercommand.domain.valueobject.UserStatus;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaveUserMessageTest {

    @Test
    void test_AllArgsConstructor() {
        String userId = UUID.randomUUID().toString();
        String name = "Name";
        String lastname = "Lastname";
        String email = "Email";
        UserStatus status = UserStatus.ACTIVE;

        SaveUserMessage actual = new SaveUserMessage(
                userId,
                name,
                lastname,
                email,
                status
        );

        assertEquals(userId, actual.id());
        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(status, actual.status());
    }
}
