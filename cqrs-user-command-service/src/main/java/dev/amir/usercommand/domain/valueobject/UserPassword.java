package dev.amir.usercommand.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class UserPassword {
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
