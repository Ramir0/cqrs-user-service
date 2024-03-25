package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.user.BirthDate;
import dev.amir.usercommand.domain.valueobject.user.FirstName;
import dev.amir.usercommand.domain.valueobject.user.Gender;
import dev.amir.usercommand.domain.valueobject.user.LastName;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateProfileRequestTest {

    @Test
    void test_AllArgsConstructor() {
        FirstName name = RandomObject.nextObject(FirstName.class);
        LastName lastname = RandomObject.nextObject(LastName.class);
        Gender gender = RandomObject.nextObject(Gender.class);
        BirthDate birthDate = RandomObject.nextObject(BirthDate.class);

        UpdateProfileRequest actual = new UpdateProfileRequest(name, lastname, gender, birthDate);

        assertEquals(name, actual.firstname());
        assertEquals(lastname, actual.lastname());
        assertEquals(gender, actual.gender());
        assertEquals(birthDate, actual.birthDate());
    }
}
