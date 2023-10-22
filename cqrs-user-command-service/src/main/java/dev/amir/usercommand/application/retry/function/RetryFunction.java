package dev.amir.usercommand.application.retry.function;

@FunctionalInterface
public interface RetryFunction<T> {
    T execute();
}
