package dev.amir.usercommand.application.retry.configuration;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@NoArgsConstructor
public class RetryConfiguration {

    @Value("${spring.retry.max-attempts}")
    private Integer maxAttempts;

    @Value("${spring.retry.initial-interval}")
    private Long initialInterval;

    @Value("${spring.retry.multiplier}")
    private Long multiplier;

    @Value("${spring.retry.max-interval}")
    private Long maxInterval;

    @Bean
    public RetryTemplate retryTemplate() {
        return RetryTemplate.builder()
                .maxAttempts(maxAttempts)
                .exponentialBackoff(initialInterval, multiplier, maxInterval)
                .retryOn(Exception.class)
                .build();
    }
}
