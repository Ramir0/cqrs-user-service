package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        String name = RandomObject.nextObject(String.class);
        String lastname = RandomObject.nextObject(String.class);
        String email = RandomObject.nextObject(String.class);
        UserStatus status = RandomObject.nextObject(UserStatus.class);
        String username = RandomObject.nextObject(String.class);
        UserGender userGender = RandomObject.nextObject(UserGender.class);

        UpdateUserRequest actual = new UpdateUserRequest(username, name, lastname, email, status, userGender);

        assertEquals(username, actual.username());
        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(status, actual.status());
        assertEquals(userGender, actual.gender());
    }
}
