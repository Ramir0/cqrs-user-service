package dev.amir.usercommand.framework.input.rest.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DeleteUserRequestTest {

    @Test
    void test_NoArgsConstructor() {
        assertDoesNotThrow(DeleteUserRequest::new);
    }
}
