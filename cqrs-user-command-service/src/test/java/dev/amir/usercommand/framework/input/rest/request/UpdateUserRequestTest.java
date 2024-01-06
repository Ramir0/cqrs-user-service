package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.RoleId;
import dev.amir.usercommand.domain.valueobject.UserEmail;
import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        RoleId roleId = RandomObject.nextObject(RoleId.class);
        UserUsername username = RandomObject.nextObject(UserUsername.class);
        UserName name = RandomObject.nextObject(UserName.class);
        UserLastName lastname = RandomObject.nextObject(UserLastName.class);
        UserEmail email = RandomObject.nextObject(UserEmail.class);
        UserStatus status = RandomObject.nextObject(UserStatus.class);
        UserGender userGender = RandomObject.nextObject(UserGender.class);

        UpdateUserRequest actual = new UpdateUserRequest(roleId, username, name, lastname, email, status, userGender);

        assertEquals(roleId, actual.roleId());
        assertEquals(username, actual.username());
        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(status, actual.status());
        assertEquals(userGender, actual.gender());
    }
}
