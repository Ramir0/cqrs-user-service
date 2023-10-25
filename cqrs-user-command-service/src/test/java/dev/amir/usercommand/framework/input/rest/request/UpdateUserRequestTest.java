package dev.amir.usercommand.framework.input.rest.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        String name = "Name";
        String lastname = "Lastname";
        String email = "Email";
        boolean isActive = true;

        UpdateUserRequest actual = new UpdateUserRequest(name, lastname, email, isActive);

        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(isActive, actual.isActive());
    }
}
