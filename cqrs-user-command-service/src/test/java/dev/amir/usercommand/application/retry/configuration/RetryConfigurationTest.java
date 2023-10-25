package dev.amir.usercommand.application.retry.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RetryConfiguration.class)
@TestPropertySource(properties = {
        "spring.retry.max-attempts=1",
        "spring.retry.initial-interval=1000",
        "spring.retry.multiplier=2",
        "spring.retry.max-interval=30000"
})
class RetryConfigurationTest {

    @Autowired
    private RetryConfiguration underTest;

    @Test
    void test_RetryTemplate() {
        RetryTemplate actual = underTest.retryTemplate();

        assertNotNull(actual);
    }
}
