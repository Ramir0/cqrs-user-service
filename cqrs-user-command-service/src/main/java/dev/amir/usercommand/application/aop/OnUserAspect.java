package dev.amir.usercommand.application.aop;

import dev.amir.usercommand.domain.entity.User;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class OnUserAspect {

    @Before("@annotation(dev.amir.usercommand.application.aop.annotation.OnUserCreation)")
    public void setCreatedAt(JoinPoint joinPoint) {
        log.info("Set 'CreatedAt' value for new user");
        findUserArg(joinPoint).ifPresent(user -> user.setCreatedAt(LocalDateTime.now()));
    }

    @Before("@annotation(dev.amir.usercommand.application.aop.annotation.OnUserUpdate)")
    public void setUpdatedAt(JoinPoint joinPoint) {
        log.info("Set 'UpdatedAt' value for existing user");
        findUserArg(joinPoint).ifPresent(user -> user.setUpdatedAt(LocalDateTime.now()));
    }

    private Optional<User> findUserArg(JoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
                .filter(object -> object instanceof User)
                .map(object -> (User) object)
                .findFirst();
    }
}
