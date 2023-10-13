package dev.amir.usercommand.application.retry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RetryExecutorImpl implements RetryExecutor {

    private final RetryTemplate retryTemplate;

    @Override
    public <T> T execute(RetryFunction<T> callback) {
        try {
            return retryTemplate.execute(retryContext -> {
                logRetryCount(retryContext);
                return callback.execute();
            });
        } catch (Exception exception) {
            log.error("Maximum attempts on retry function", exception);
        }

        return null;
    }

    @Override
    public void execute(RetryAction callback) {
        try {
            retryTemplate.execute(retryContext -> {
                logRetryCount(retryContext);
                callback.execute();
                return Void.TYPE;
            });
        } catch (Exception exception) {
            log.error("Maximum attempts on retry action", exception);
        }
    }

    @Override
    @Async
    public void asyncExecute(RetryAction callback) {
        execute(callback);
    }

    private void logRetryCount(RetryContext retryContext) {
        if (retryContext.getRetryCount() > 0) {
            log.warn(
                    "Retry count: [{}] Error message: [{}]",
                    retryContext.getRetryCount(),
                    retryContext.getLastThrowable().getMessage()
            );
        }
    }
}
