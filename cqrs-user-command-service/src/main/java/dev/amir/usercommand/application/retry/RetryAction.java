package dev.amir.usercommand.application.retry;

@FunctionalInterface
public interface RetryAction {
    void execute();
}