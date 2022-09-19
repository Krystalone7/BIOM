package com.biom.controller.advice.exception;

public class UserProfileNotExistException extends RuntimeException{
    public UserProfileNotExistException(String message) {
        super(message);
    }
}
