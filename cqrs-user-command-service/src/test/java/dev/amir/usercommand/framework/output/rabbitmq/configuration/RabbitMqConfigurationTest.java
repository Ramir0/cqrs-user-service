package dev.amir.usercommand.framework.output.rabbitmq.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RabbitMqConfigurationTest {
    private RabbitMqConfiguration underTest;

    @BeforeEach
    void setUp() {
        underTest = new RabbitMqConfiguration();
    }

    @Test
    void test_RabbitTemplate() {
        CachingConnectionFactory factory = new CachingConnectionFactory();

        RabbitTemplate actual = underTest.rabbitTemplate(factory);

        assertNotNull(actual);
    }

    @Test
    void test_CreateUsersQueue() {
        String queueName = "test-queue";
        ReflectionTestUtils.setField(underTest, "usersQueue", queueName);
        Queue actual = underTest.createUsersQueue();

        assertNotNull(actual);
        assertEquals(queueName, actual.getName());
    }
}
