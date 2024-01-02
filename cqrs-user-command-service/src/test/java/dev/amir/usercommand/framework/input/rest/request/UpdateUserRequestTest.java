package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.util.RandomObject;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        UUID roleId = RandomObject.nextObject(UUID.class);
        UserName name = RandomObject.nextObject(UserName.class);
        UserLastName lastname = RandomObject.nextObject(UserLastName.class);
        String email = RandomObject.nextObject(String.class);
        UserStatus status = RandomObject.nextObject(UserStatus.class);
        String userName = RandomObject.nextObject(String.class);
        UserGender userGender = RandomObject.nextObject(UserGender.class);

        UpdateUserRequest actual = new UpdateUserRequest(roleId, name, lastname, email, status, userName, userGender);

        assertEquals(roleId, actual.roleId());
        assertEquals(userName, actual.username());
        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(status, actual.status());
        assertEquals(userGender, actual.gender());
    }
}
