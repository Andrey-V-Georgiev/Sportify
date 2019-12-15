package com.softuni.sportify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CreateException extends Exception {

    public CreateException() {
    }

    public CreateException(String message) {
        super(message);
    }
}
