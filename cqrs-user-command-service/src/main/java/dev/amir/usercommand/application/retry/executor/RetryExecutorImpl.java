package dev.amir.usercommand.application.retry.executor;

import dev.amir.usercommand.application.retry.action.RetryAction;
import dev.amir.usercommand.application.retry.action.RetryActionCallback;
import dev.amir.usercommand.application.retry.function.RetryFunction;
import dev.amir.usercommand.application.retry.function.RetryFunctionCallback;
import dev.amir.usercommand.domain.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            return retryTemplate.execute(
                    new RetryFunctionCallback<T, Exception>(callback)
            );
        } catch (UserException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new RuntimeException("Maximum attempts on retry function", exception);
        }
    }

    @Override
    public void execute(RetryAction callback) {
        try {
            retryTemplate.execute(
                    new RetryActionCallback<Exception>(callback)
            );
        } catch (UserException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new RuntimeException("Maximum attempts on retry action", exception);
        }
    }

    @Override
    @Async
    public void asyncExecute(RetryAction callback) {
        execute(callback);
    }
}
