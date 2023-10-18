package dev.amir.userquery;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class UserQueryApplicationTest {
    @Test
    void test_Main() {
        try (MockedStatic<SpringApplication> springAppMock = mockStatic(SpringApplication.class)) {
            String arg1 = "arg1";
            springAppMock.when(
                    () -> SpringApplication.run(any(Class.class), any(String[].class))
            ).thenReturn(null);

            UserQueryApplication.main(new String[] {arg1});

            springAppMock.verify(
                    () -> SpringApplication.run(eq(UserQueryApplication.class), eq(arg1))
            );
        }
    }

    @Test
    void test_NoArgsConstructor() throws
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        UserQueryApplication app = UserQueryApplication.class.getDeclaredConstructor().newInstance();
        assertNotNull(app);
    }
}
