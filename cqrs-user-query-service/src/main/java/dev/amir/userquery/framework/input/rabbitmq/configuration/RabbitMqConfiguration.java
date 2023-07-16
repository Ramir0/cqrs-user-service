package dev.amir.userquery.framework.input.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class RabbitMqConfiguration {
    @Value("${spring.rabbitmq.queues.users-queue}")
    private String usersQueue;

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue createResourceProcessQueue() {
        return new Queue(usersQueue);
    }
}
