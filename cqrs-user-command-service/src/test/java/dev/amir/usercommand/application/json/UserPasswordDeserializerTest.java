package dev.amir.usercommand.application.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.util.RandomObject;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserPasswordDeserializerTest {
    @Mock
    private JsonParser parserMock;
    @Mock
    private DeserializationContext contextMock;
    @Mock
    private PasswordEncoder encoderMock;
    @InjectMocks
    private UserPasswordDeserializer underTest;

    @Test
    void test_Deserialize_ReturnsEncryptedPassword() throws IOException {
        String plainPassword = RandomObject.nextObject(String.class);
        String password = RandomObject.nextObject(String.class);

        when(parserMock.getValueAsString()).thenReturn(plainPassword);
        when(encoderMock.encode(anyString())).thenReturn(password);

        UserPassword userPassword = underTest.deserialize(parserMock, contextMock);

        assertEquals(password, userPassword.value());
        verify(parserMock).getValueAsString();
        verify(encoderMock).encode(eq(plainPassword));
    }
}
