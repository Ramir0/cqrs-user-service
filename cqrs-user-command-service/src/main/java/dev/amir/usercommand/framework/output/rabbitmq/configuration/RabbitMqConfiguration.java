package dev.amir.usercommand.framework.output.rabbitmq.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMqConfiguration {
    @Value("${spring.rabbitmq.queues.users-queue}")
    private String usersQueue;

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        log.info("Creating RabbitTemplate");
        var rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        log.info("RabbitTemplate created");
        return rabbitTemplate;
    }

    @Bean
    public Queue createUsersQueue() {
        Queue queue = new Queue(usersQueue);
        log.info("Queue for users created");
        return queue;
    }
}
