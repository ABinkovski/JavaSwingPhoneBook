package com.edu.pb.domain.service;

import com.edu.pb.domain.model.User;
import com.edu.pb.domain.model.exception.AuthUserException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

@RequiredArgsConstructor
public class AuthService {

    private static final String AUTH_ERROR_MESSAGE = "User name or password is wrong";

    private final Set<String> validPassword;

    public void validateUser(final User user) throws AuthUserException {
        validateUserName(user.getUserName());
        validatePassword(user.getPassword());
    }

    private void validateUserName(final String username) throws AuthUserException {
        if(StringUtils.isBlank(username)) {
            throw new AuthUserException(AUTH_ERROR_MESSAGE);
        }
    }

    private void validatePassword(final char[] password) throws AuthUserException {
        if(!validPassword.contains(new String(password))) {
            throw new AuthUserException(AUTH_ERROR_MESSAGE);
        }
    }

}
