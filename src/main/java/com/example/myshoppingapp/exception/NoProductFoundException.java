package com.example.myshoppingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoProductFoundException extends Exception {

    public NoProductFoundException(String message) {
        super(message);
    }

}
