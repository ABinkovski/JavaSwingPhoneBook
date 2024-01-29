package com.edu.pb.domain.model.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

@Deprecated(since = "It is not recommended to specify auth error detail in production,\n" +
        "But it is fine to do for test project.")
public class InvalidPasswordException extends AuthUserException {
    public InvalidPasswordException(final Collection<String> validPasswords) {
        super(String.format("Password should be one of: %s",
                StringUtils.join(validPasswords)));
    }
}
