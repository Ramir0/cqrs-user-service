package dev.amir.usercommand.application.json.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.amir.usercommand.domain.validator.PasswordValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = JsonConfiguration.class)
class JsonConfigurationTest {
    @Mock
    private PasswordEncoder passwordEncoderMock;
    @Mock
    private PasswordValidator passwordValidatorMock;
    @InjectMocks
    private JsonConfiguration underTest;

    @Test
    void test_ObjectMapper() {
        ObjectMapper mapper = underTest.objectMapper(passwordEncoderMock, passwordValidatorMock);

        assertNotNull(mapper);
    }
}
