package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        String name = "Name";
        String lastname = "Lastname";
        String email = "Email";
        UserStatus status = UserStatus.ACTIVE;

        CreateUserRequest actual = new CreateUserRequest(name, lastname, email, status);

        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(status, actual.status());
    }
}
