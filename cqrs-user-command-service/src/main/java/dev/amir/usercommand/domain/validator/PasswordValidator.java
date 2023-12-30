package dev.amir.usercommand.domain.validator;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static dev.amir.usercommand.domain.validator.AttributeErrorType.EMPTY_VALUE;
import static dev.amir.usercommand.domain.validator.AttributeErrorType.INVALID_FORMAT;
import static dev.amir.usercommand.domain.validator.AttributeErrorType.INVALID_VALUE;
import static dev.amir.usercommand.domain.validator.AttributeErrorType.MAX_LIMIT_EXCEEDED;
import static dev.amir.usercommand.domain.validator.AttributeErrorType.MIN_LIMIT_NOT_REACHED;

@Service
public class PasswordValidator implements AttributeValidator<String> {
    private static final int MIN_ALLOWED = 6;
    private static final int MAX_ALLOWED = 50;
    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile(
            "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)"
    );

    @Override
    public Set<AttributeErrorType> validate(String value) {
        Set<AttributeErrorType> errors = new HashSet<>();

        if (!StringUtils.hasText(value)) {
            errors.add(EMPTY_VALUE);
            return errors;
        }

        if (value.length() < MIN_ALLOWED) {
            errors.add(MIN_LIMIT_NOT_REACHED);
        } else if (value.length() > MAX_ALLOWED) {
            errors.add(MAX_LIMIT_EXCEEDED);
        }

        Matcher matcher = ALLOWED_CHARACTERS_PATTERN.matcher(value);
        if (!matcher.find()) {
            errors.add(INVALID_FORMAT);
        }

        if (StringUtils.containsWhitespace(value)) {
            errors.add(INVALID_VALUE);
        }

        return errors;
    }
}
