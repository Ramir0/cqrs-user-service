package dev.amir.authorizationserver;

import dev.amir.authorizationserver.util.RandomObject;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class AuthorizationServerApplicationTest {

    @Test
    void test_Main() {
        try (MockedStatic<SpringApplication> springAppMock = mockStatic(SpringApplication.class)) {
            String arg1 = RandomObject.nextObject(String.class);
            springAppMock.when(
                    () -> SpringApplication.run(any(Class.class), any(String[].class))
            ).thenReturn(null);

            AuthorizationServerApplication.main(new String[]{arg1});

            springAppMock.verify(
                    () -> SpringApplication.run(eq(AuthorizationServerApplication.class), eq(arg1))
            );
        }
    }
}