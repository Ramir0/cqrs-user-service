package dev.amir.usercommand.application.retry.executor;

import dev.amir.usercommand.application.retry.action.RetryAction;
import dev.amir.usercommand.application.retry.function.RetryFunction;

public interface RetryExecutor {
    <T> T execute(RetryFunction<T> callback);

    void execute(RetryAction callback);

    void asyncExecute(RetryAction callback);
}
