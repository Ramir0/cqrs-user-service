package dev.amir.usercommand.application.retry.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;

@Slf4j
@RequiredArgsConstructor
public class RetryActionCallback<R extends Exception> implements RetryCallback<Void, R> {

    private final RetryAction callback;

    @Override
    public Void doWithRetry(RetryContext retryContext) {
        logRetryCount(retryContext);
        callback.execute();
        return null;
    }

    private void logRetryCount(RetryContext retryContext) {
        if (retryContext.getRetryCount() > 0) {
            log.warn(
                    "Retry Action count: [{}] Error message: [{}]",
                    retryContext.getRetryCount(),
                    retryContext.getLastThrowable().getMessage()
            );
        }
    }
}
