package com.edu.pb.domain.model.exception;

import lombok.RequiredArgsConstructor;

public class AuthUserException extends Exception {
    public AuthUserException(final String message) {
        super(message);
    }
}
