package com.example.myshoppingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoCartItemFound extends Throwable {

    public NoCartItemFound(String message) {
        super(message);
    }

}
