package dev.amir.usercommand.framework.output.rabbitmq.message;

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
        boolean isActive = true;

        SaveUserMessage actual = new SaveUserMessage(
                userId,
                name,
                lastname,
                email,
                isActive
        );

        assertEquals(userId, actual.id());
        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(isActive, actual.active());
    }
}
