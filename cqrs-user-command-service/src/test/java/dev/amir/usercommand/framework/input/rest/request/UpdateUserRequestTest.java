package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        String name = "Name";
        String lastname = "Lastname";
        String email = "Email";
        UserStatus status = UserStatus.ACTIVE;
        String userName = "UserName";
        UserGender userGender = UserGender.MALE;

        UpdateUserRequest actual = new UpdateUserRequest(name, lastname, email, status, userName, userGender);

        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(status, actual.status());
    }
}
