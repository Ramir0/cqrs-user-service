package dev.amir.usercommand.application.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import dev.amir.usercommand.domain.exception.UserPasswordValidationException;
import dev.amir.usercommand.domain.validator.AttributeErrorType;
import dev.amir.usercommand.domain.validator.AttributeValidator;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.util.RandomObject;
import java.io.IOException;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
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
    @Mock
    private AttributeValidator<String> validatorMock;
    @InjectMocks
    private UserPasswordDeserializer underTest;

    @Test
    void test_Deserialize_ReturnsEncryptedPassword() throws IOException {
        String plainPassword = RandomObject.nextObject(String.class);
        String password = RandomObject.nextObject(String.class);

        when(parserMock.getValueAsString()).thenReturn(plainPassword);
        when(validatorMock.validate(anyString())).thenReturn(Set.of());
        when(encoderMock.encode(anyString())).thenReturn(password);

        UserPassword userPassword = underTest.deserialize(parserMock, contextMock);

        assertEquals(password, userPassword.value());
        verify(parserMock).getValueAsString();
        verify(validatorMock).validate(eq(plainPassword));
        verify(encoderMock).encode(eq(plainPassword));
    }

    @Test
    void test_Deserialize_ThrowsUserPasswordValidationException() throws IOException {
        String plainPassword = RandomObject.nextObject(String.class);
        Set<AttributeErrorType> errors = Set.of(AttributeErrorType.INVALID_VALUE);

        when(parserMock.getValueAsString()).thenReturn(plainPassword);
        when(validatorMock.validate(anyString())).thenReturn(errors);

        UserPasswordValidationException exception = assertThrows(
                UserPasswordValidationException.class,
                () -> underTest.deserialize(parserMock, contextMock)
        );

        assertEquals(errors, exception.getErrors());
        verify(parserMock).getValueAsString();
        verify(validatorMock).validate(eq(plainPassword));
        verify(encoderMock, never()).encode(eq(plainPassword));
    }
}
