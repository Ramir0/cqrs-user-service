package dev.amir.userquery.framework.input.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class RabbitMqConfiguration {
    @Value("${spring.rabbitmq.queues.users-queue}")
    private String usersQueue;

    @Bean
    public Jackson2JsonMessageConverter converter() {
        log.info("Jackson2JsonMessageConverter,Configuration done.");
        return new Jackson2JsonMessageConverter();
        
    }

    @Bean
    public Queue createUsersQueue() {
      log.info("User queue created user name.");
        return new Queue(usersQueue);
    }
}
