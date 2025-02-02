package com.leticia.api.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {
        super("User already exists!");
    }
}
