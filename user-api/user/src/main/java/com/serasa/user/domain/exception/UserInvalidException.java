package com.serasa.user.domain.exception;

public class UserInvalidException extends RuntimeException {
    public UserInvalidException(String message) {
        super(message);
    }
}
