package dev.amir.usercommand.application.retry;

public interface RetryExecutor {
    <T> T execute(RetryFunction<T> callback);

    void execute(RetryAction callback);

    void asyncExecute(RetryAction callback);
}
