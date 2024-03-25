package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.Email;
import dev.amir.usercommand.domain.valueobject.user.FirstName;
import dev.amir.usercommand.domain.valueobject.user.LastName;
import dev.amir.usercommand.domain.valueobject.user.Password;
import dev.amir.usercommand.domain.valueobject.user.Username;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        RoleId roleId = RandomObject.nextObject(RoleId.class);
        Username userName = RandomObject.nextObject(Username.class);
        Password password = RandomObject.nextObject(Password.class);
        FirstName firstName = RandomObject.nextObject(FirstName.class);
        LastName lastname = RandomObject.nextObject(LastName.class);
        Email email = RandomObject.nextObject(Email.class);

        CreateUserRequest actual = new CreateUserRequest(
                roleId,
                userName,
                password,
                firstName,
                lastname,
                email
        );

        assertEquals(roleId, actual.roleId());
        assertEquals(userName, actual.username());
        assertEquals(password, actual.password());
        assertEquals(firstName, actual.firstName());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
    }
}
