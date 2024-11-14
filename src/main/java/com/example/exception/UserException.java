package com.example.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// user/exception/
@Getter
public abstract class UserException extends RuntimeException{

    public UserException(String message) {
        super(message);
    }
    public abstract HttpStatus getStatusCode();
}

