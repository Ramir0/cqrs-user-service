package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.util.RandomObject;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        UUID roleId = RandomObject.nextObject(UUID.class);
        String username = RandomObject.nextObject(String.class);
        UserPassword password = RandomObject.nextObject(UserPassword.class);
        String name = RandomObject.nextObject(String.class);
        String lastname = RandomObject.nextObject(String.class);
        String email = RandomObject.nextObject(String.class);
        UserStatus status = RandomObject.nextObject(UserStatus.class);
        UserGender userGender = RandomObject.nextObject(UserGender.class);

        CreateUserRequest actual = new CreateUserRequest(
                roleId,
                username,
                password,
                name,
                lastname,
                email,
                status,
                userGender
        );

        assertEquals(roleId, actual.roleId());
        assertEquals(username, actual.username());
        assertEquals(password, actual.password());
        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(status, actual.status());
        assertEquals(userGender, actual.gender());
    }
}
