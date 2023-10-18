package dev.amir.userquery.framework.input.rabbitmq.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RabbitMqConfigurationTest {

    private RabbitMqConfiguration underTest;

    @BeforeEach
    void setUp() {
        underTest = new RabbitMqConfiguration();
    }

    @Test
    void test_Converter() {
        Jackson2JsonMessageConverter actual = underTest.converter();

        assertNotNull(actual);
    }

    @Test
    void test_CreateUsersQueue() {
        ReflectionTestUtils.setField(underTest, "usersQueue", "q.users");

        Queue actual = underTest.createUsersQueue();

        assertNotNull(actual);
    }
}
