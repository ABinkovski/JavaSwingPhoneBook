package com.edu.pb.domain.service;

import com.edu.pb.domain.model.User;
import com.edu.pb.domain.model.exception.AuthUserException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

@RequiredArgsConstructor
public class AuthService {

    private final Set<String> validPassword;

    public void validateUser(final User user) throws AuthUserException {
        validateUserName(user.getUserName());
        validatePassword(user.getPassword());
    }

    private void validateUserName(final String username) throws AuthUserException {
        if(StringUtils.isBlank(username)) {
            throw new AuthUserException();
        }
    }

    private void validatePassword(final char[] password) throws AuthUserException {
        if(!validPassword.contains(new String(password))) {
            throw new AuthUserException();
        }
    }

}
