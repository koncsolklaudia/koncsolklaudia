package com.example.basicwebshop.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class WSException extends Exception{

    private HttpStatus httpStatus;

    public WSException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public WSException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
