package dev.amir.usercommand.application.retry.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;

@Slf4j
@RequiredArgsConstructor
public class RetryFunctionCallback<T, R extends Exception> implements RetryCallback<T, R> {

    private final RetryFunction<T> callback;

    @Override
    public T doWithRetry(RetryContext retryContext) {
        logRetryCount(retryContext);
        return callback.execute();
    }

    private void logRetryCount(RetryContext retryContext) {
        if (retryContext.getRetryCount() > 0) {
            log.warn(
                    "Retry Function count: [{}] Error message: [{}]",
                    retryContext.getRetryCount(),
                    retryContext.getLastThrowable().getMessage()
            );
        }
    }
}
