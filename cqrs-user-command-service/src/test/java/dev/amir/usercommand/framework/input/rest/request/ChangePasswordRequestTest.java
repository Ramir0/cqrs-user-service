package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.user.Password;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChangePasswordRequestTest {

    @Test
    void test_AllArgsConstructor() {
        Password password = RandomObject.nextObject(Password.class);

        ChangePasswordRequest actual = new ChangePasswordRequest(password);

        assertNotNull(actual);
    }
}
