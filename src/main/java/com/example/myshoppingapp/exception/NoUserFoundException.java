package com.example.myshoppingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoUserFoundException extends Exception {

    public NoUserFoundException(String message) {
        super(message);
    }

}
