package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserBirthDate;
import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.util.RandomObject;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateProfileRequestTest {

    @Test
    void test_AllArgsConstructor() {
        UserName name = RandomObject.nextObject(UserName.class);
        UserLastName lastname = RandomObject.nextObject(UserLastName.class);
        UserGender userGender = RandomObject.nextObject(UserGender.class);
        UserBirthDate birthDate = RandomObject.nextObject(UserBirthDate.class);

        UpdateProfileRequest actual = new UpdateProfileRequest(name, lastname, userGender, birthDate);

        assertEquals(name, actual.name());
        assertEquals(lastname, actual.lastname());
        assertEquals(userGender, actual.gender());
        assertEquals(birthDate, actual.birthDate());
    }
}
