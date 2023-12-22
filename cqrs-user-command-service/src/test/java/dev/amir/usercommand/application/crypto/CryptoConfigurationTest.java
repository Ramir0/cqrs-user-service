package dev.amir.usercommand.application.crypto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CryptoConfiguration.class)
class CryptoConfigurationTest {

    @Autowired
    private CryptoConfiguration underTest;

    @Test
    void test_passwordEncoder() {
        PasswordEncoder actual = underTest.passwordEncoder();

        assertNotNull(actual);
    }
}
