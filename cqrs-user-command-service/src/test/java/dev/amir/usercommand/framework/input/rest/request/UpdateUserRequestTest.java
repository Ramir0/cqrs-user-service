package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.Email;
import dev.amir.usercommand.domain.valueobject.user.FirstName;
import dev.amir.usercommand.domain.valueobject.user.Gender;
import dev.amir.usercommand.domain.valueobject.user.LastName;
import dev.amir.usercommand.domain.valueobject.user.Status;
import dev.amir.usercommand.domain.valueobject.user.Username;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserRequestTest {

    @Test
    void test_AllArgsConstructor() {
        RoleId roleId = RandomObject.nextObject(RoleId.class);
        Username userName = RandomObject.nextObject(Username.class);
        FirstName name = RandomObject.nextObject(FirstName.class);
        LastName lastname = RandomObject.nextObject(LastName.class);
        Email email = RandomObject.nextObject(Email.class);
        Status status = RandomObject.nextObject(Status.class);
        Gender gender = RandomObject.nextObject(Gender.class);

        UpdateUserRequest actual = new UpdateUserRequest(roleId, userName, name, lastname, email, status, gender);

        assertEquals(roleId, actual.roleId());
        assertEquals(userName, actual.username());
        assertEquals(name, actual.firstname());
        assertEquals(lastname, actual.lastname());
        assertEquals(email, actual.email());
        assertEquals(status, actual.status());
        assertEquals(gender, actual.gender());
    }
}
