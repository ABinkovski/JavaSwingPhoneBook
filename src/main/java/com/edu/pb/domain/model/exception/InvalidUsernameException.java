package com.edu.pb.domain.model.exception;

@Deprecated(since = "It is not recommended to specify auth error detail in production,\n" +
        "But it is fine to do for test project.")
public class InvalidUsernameException extends AuthUserException {
    public InvalidUsernameException() {
        super("Username shouldn't be empty");
    }
}
