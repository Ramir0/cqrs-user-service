package dev.amir.usercommand.framework.input.rest.response;

import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateUserResponseTest {

    @Test
    void test_AllArgsConstructor() {
        String userId = UUID.randomUUID().toString();
        CreateUserResponse actual = new CreateUserResponse(userId);

        assertEquals(userId, actual.id());
    }
}
