package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.UserEmail;
import dev.amir.usercommand.domain.valueobject.user.UserLastName;
import dev.amir.usercommand.domain.valueobject.user.UserName;
import dev.amir.usercommand.domain.valueobject.user.UserPassword;
import dev.amir.usercommand.domain.valueobject.user.UserUsername;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        RoleId roleId = RandomObject.nextObject(RoleId.class);
        UserUsername username = RandomObject.nextObject(UserUsername.class);
        UserPassword password = RandomObject.nextObject(UserPassword.class);
        UserName name = RandomObject.nextObject(UserName.class);
        UserLastName lastname = RandomObject.nextObject(UserLastName.class);
        UserEmail email = RandomObject.nextObject(UserEmail.class);

        CreateUserRequest actual = new CreateUserRequest(
                roleId,
                username,
                password,
                name,
                lastname,
                email
        );

        assertEquals(roleId, actual.roleId());
        assertEquals(username, actual.username());
        assertEquals(password, actual.password());
        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
    }
}
