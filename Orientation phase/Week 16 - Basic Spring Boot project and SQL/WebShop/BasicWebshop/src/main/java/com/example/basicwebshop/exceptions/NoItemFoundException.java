package com.example.basicwebshop.exceptions;

import org.springframework.http.HttpStatus;

public class NoItemFoundException extends WSException {
    public NoItemFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
