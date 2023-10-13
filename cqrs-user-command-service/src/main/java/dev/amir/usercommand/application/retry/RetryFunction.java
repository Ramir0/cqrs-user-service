package dev.amir.usercommand.application.retry;

@FunctionalInterface
public interface RetryFunction<T> {
    T execute();
}
