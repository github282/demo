package com.cloudmusic.entity.user;

import org.springframework.security.core.AuthenticationException;

public class ValidateUserException extends AuthenticationException {

    public ValidateUserException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateUserException(String msg) {
        super(msg);
    }
}
