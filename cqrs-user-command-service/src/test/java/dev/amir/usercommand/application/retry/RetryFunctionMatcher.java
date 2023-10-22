package dev.amir.usercommand.application.retry;

import dev.amir.usercommand.application.retry.function.RetryFunction;
import org.mockito.ArgumentMatcher;

public class RetryFunctionMatcher<T> implements ArgumentMatcher<RetryFunction<T>> {

    @Override
    public boolean matches(RetryFunction<T> argument) {
        return argument != null;
    }
}
